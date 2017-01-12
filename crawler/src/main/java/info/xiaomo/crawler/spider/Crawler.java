//package info.xiaomo.crawler.spider;
//
//import okhttp3.*;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class Crawler {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);
//    private final Set<HttpUrl> fetchedUrls = Collections.synchronizedSet(new LinkedHashSet<HttpUrl>());
//    private final BlockingQueue<HttpUrl> queue = new LinkedBlockingQueue<>();
//    private final ConcurrentMap<String, AtomicInteger> hostnames = new ConcurrentHashMap<>();
//    private OkHttpClient client = null;
//
//    private Crawler() {
//        init();
//    }
//
//    private static Crawler getInstance() {
//        return CrawlerHolder.INSTANCE;
//    }
//
//    public static void main(String[] args) throws ExecutionException, IntCodeuptedException {
//        String[] urls = {"https://www.baidu.com/"};
//        List<Future<String>> results = Crawler.getInstance().initUrl(urls).parallelDrainQueue(3);
//        for (Future<String> future : results) {
//            System.out.println(future.get());
//        }
//    }
//
//    private Crawler initUrl(String[] urls) {
//        for (String url : urls) {
//            queue.add(HttpUrl.parse(url));
//        }
//
//        return this;
//    }
//
//    private void init() {
//        long cacheByteCount = 1024 * 1024 * 100;
//        String dir = "C:\\test";
//        Cache cache = new Cache(new File(dir), cacheByteCount);
//        client = new OkHttpClient.Builder().cache(cache).build();
//    }
//
//    public List<Future<String>> parallelDrainQueue(int threadCount) {
//        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
//        List<Future<String>> results = new ArrayList<>();
//        for (int i = 0; i < threadCount; i++) {
//            Future<String> future = executor.submit(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    try {
//                        drainQueue();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//            });
//
//            results.add(future);
//        }
//        return results;
//    }
//
//    private void drainQueue() throws Exception {
//        for (HttpUrl url; (url = queue.take()) != null; ) {
//            if (!fetchedUrls.add(url)) {
//                continue;
//            }
//
//            try {
//                fetch(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void fetch(HttpUrl url) throws IOException {
//        AtomicInteger hostnameCount = new AtomicInteger();
//        AtomicInteger previous = hostnames.putIfAbsent(url.host(), hostnameCount);
//        if (previous != null) {
//            hostnameCount = previous;
//        }
//
//        if (hostnameCount.incrementAndGet() > 100) {
//            return;
//        }
//
//        Request request = new Request.Builder().url(url).build();
//        Response response = client.newCall(request).execute();
//        String responseSource = response.networkResponse() != null
//                ? ("(network: " + response.networkResponse().code() + " over " + response.protocol() + ")") : "(cache)";
//        int responseCode = response.code();
//
//        // 打印log
//        LOGGER.info("ThreadName:【{}】,ResponseCode:【{}】,URL:【{}】,ResponseSource:【{}】", Thread.currentThread().getName(),
//                responseCode, url, responseSource);
//
//        String contentType = response.header("Content-Type");
//        if (responseCode != 200 || contentType == null) {
//            response.body().close();
//            return;
//        }
//
//        MediaType mediaType = MediaType.parse(contentType);
//        if (mediaType == null || !mediaType.subtype().equalsIgnoreCase("html")) {
//            response.body().close();
//            return;
//        }
//
//        // 获取页面的a[href], 加入LinkedBlockingQueue
//        Document document = Jsoup.parse(response.body().string(), url.toString());
//        for (Element element : document.select("a[href]")) {
//            String href = element.attr("href");
//            HttpUrl link = response.request().url().resolve(href);
//            if (link != null) {
//                queue.add(link);
//            }
//        }
//    }
//
//    private static class CrawlerHolder {
//        private static final Crawler INSTANCE = new Crawler();
//    }
//}
