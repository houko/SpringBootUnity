/**
 *
 * Created by Administrator on 2016/4/11.
 * - EpicEditor：太丑，代码不高亮，没有工具栏
 - Bootstrap-Markdown：不能多行代码，没有实时预览
 - PageDown：不能多行代码，使用不方便
 - zepto（没记错的话）：太重，是一个框架
 */
$(function(){
    var option={
        target:'.suMarkdown',
        preview:true
    };
    SuMarkdown(option);
});