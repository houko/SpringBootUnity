package info.xiaomo.fileupload;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FileUploadMain.class)
@AutoConfigureMockMvc
// 落到构建目录, 不污染工作区
@TestPropertySource(properties = "app.upload.dir=./target/test-upload")
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 上传后应当能按返回的文件名下载回原内容() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "hello.txt", "text/plain", "你好, SpringBootUnity".getBytes(StandardCharsets.UTF_8));

        String body = mockMvc.perform(multipart("/file/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value(200))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String stored = body.replaceAll(".*\"data\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        // 保留了原扩展名, 但文件名本身是随机的
        assertThat(stored).endsWith(".txt").doesNotContain("hello");

        byte[] downloaded = mockMvc.perform(get("/file/download/{name}", stored))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + stored + "\""))
                .andReturn().getResponse().getContentAsByteArray();

        assertThat(new String(downloaded, StandardCharsets.UTF_8)).isEqualTo("你好, SpringBootUnity");
    }

    @Test
    void 批量上传应当返回与文件数量相同的文件名() throws Exception {
        MockMultipartFile a = new MockMultipartFile("files", "a.txt", "text/plain", "aaa".getBytes());
        MockMultipartFile b = new MockMultipartFile("files", "b.txt", "text/plain", "bbb".getBytes());

        mockMvc.perform(multipart("/file/upload/batch").file(a).file(b))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void 上传空文件应当被拒绝() throws Exception {
        MockMultipartFile empty = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        mockMvc.perform(multipart("/file/upload").file(empty))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("上传的文件为空"));
    }

    @Test
    void 下载不存在的文件应当返回400而不是500() throws Exception {
        mockMvc.perform(get("/file/download/{name}", "not-there.txt"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 带路径穿越片段的文件名应当被拒绝() throws Exception {
        mockMvc.perform(get("/file/download/{name}", "..%2F..%2Fpom.xml"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void 不符合存储命名格式的文件名应当被拒绝() throws Exception {
        mockMvc.perform(get("/file/download/{name}", "pom.xml"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("非法的文件名: pom.xml"));
    }

    @Test
    void 原始文件名中的路径穿越片段不得混入扩展名() throws Exception {
        // 按"最后一个点之后"取扩展名会得到 .b/../../evil, 若不做处理就会写到上传目录之外
        MockMultipartFile evil = new MockMultipartFile(
                "file", "a.b/../../evil", "text/plain", "payload".getBytes());

        String body = mockMvc.perform(multipart("/file/upload").file(evil))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String stored = body.replaceAll(".*\"data\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        // 扩展名没有通过白名单, 被整个丢弃, 只留下服务端生成的 UUID
        assertThat(stored).doesNotContain("..").doesNotContain("/").doesNotContain("evil");
        assertThat(stored).matches("[0-9a-f-]{36}");
    }

    @Test
    void 合法扩展名应当被保留() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "report.PDF", "application/pdf", "pdf".getBytes());

        String body = mockMvc.perform(multipart("/file/upload").file(file))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertThat(body.replaceAll(".*\"data\"\\s*:\\s*\"([^\"]+)\".*", "$1")).endsWith(".PDF");
    }

}
