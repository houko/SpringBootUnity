/**
 * Created by Administrator on 2016/4/11.
 */
/**
 *
 * Created by suemi on 14-12-5.
 */
var SuMarkdown=function(option){

    var load=function(mark){
        var get=function(dst){
            var result={};
            $(dst).each(function(i,e){
                result.select=(
                    // Mozilla, Webkit
                    ('selectionStart' in e && function()
                    {
                        var l = e.selectionEnd - e.selectionStart;
                        return {
                            start: e.selectionStart,
                            end: e.selectionEnd,
                            length: l,
                            text: e.value.substr(e.selectionStart, l)
                        };
                    }) ||
                    // Internet Explorer
                    (document.selection && function()
                    {
                        e.focus();
                        var r = document.selection.createRange();
                        if (r === null)
                        {
                            return {
                                start: 0,
                                end: e.value.length,
                                length: 0
                            };
                        }
                        var re = e.createTextRange();
                        var rc = re.duplicate();
                        re.moveToBookmark(r.getBookmark());
                        rc.setEndPoint('EndToStart', re);
                        return {
                            start: rc.text.length,
                            end: rc.text.length + r.text.length,
                            length: r.text.length,
                            text: r.text
                        };
                    }) ||
                    // Not supported
                    function()
                    {
                        return null;
                    }
                )();
                if(result.select) {
                    result.container=$(this);
                    return false;
                }
                else return true;
            });
            return result;
        };
        var replace= function (target,text,option){
            var tmp;
            $(target).each(function(i,e){
                tmp=(
                    // Mozilla, Webkit
                    ('selectionStart' in e && function()
                    {
                        var start = e.selectionStart;
                        e.value = e.value.substr(0, e.selectionStart) + text + e.value.substr(e.selectionEnd, e.value.length);
                        if (option === true || option === undefined)
                        {
                            e.selectionStart = start;
                            e.selectionEnd = start + text.length;
                        }
                        else
                        {
                            e.selectionStart = e.selectionEnd = start + text.length;
                        }
                        return $(e);
                    }) ||
                    // Internet Explorer
                    (document.selection && function()
                    {
                        e.focus();
                        document.selection.createRange().text = text;
                        return $(e);
                    }) ||
                    // Not supported
                    function()
                    {
                        e.value += text;
                        return $(e);
                    }
                )();
                if(tmp) return false;
                else return true;
            });
            return tmp;
        };
        var methods={
            bold:function(){
                var reg=/^\*{2}[^\0]*\*{2}$/m;
                var target=get($('textarea',mark));
                var dst;
                if(!reg.test(target.select.text))
                    dst=replace(target.container,'**'+target.select.text+'**');

                else dst=replace(target.container,target.select.text.split('**')[1]);

                return dst;
            },
            italic:function(){
                var reg=/^_[^\0]*_$/m;
                var target=get($('textarea',mark));
                var dst;
                if(!reg.test(target.select.text))
                    dst=replace(target.container,'_'+target.select.text+'_');
                else dst=replace(target.container,target.select.text.split('_')[1]);
                return dst;
            },
            head:function(){
                var reg=/^#{1,6}[^\0]*/m;
                var target=get($('textarea',mark));
                var dst;
                if(!reg.test(target.select.text))
                    dst=replace(target.container,'###'+target.select.text);
                else dst=replace(target.container,target.select.text.split(/#{1,6}/)[1]);
                return dst;
            },
            link:function(){
                var reg=/^\[[^\0]*\]\([^\0]*\)$/m;
                var target=get($('textarea',mark));
                var dst;

                if(!reg.test(target.select.text)) {
                    var url=prompt('Enter your URL :');
                    if(!url) return target.container;
                    if(target.select.text=='') target.select.text="Enter your link description here:";
                    dst = replace(target.container, '[ ' + target.select.text + ' ](' + url + ')');
                }
                else dst=replace(target.container,target.select.text.split('[')[1].split(']')[0]);
                return dst;
            },
            img:function(){
                var reg=/^!\[[^\0]*\]\([^\0]*\)$/m;
                var target=get($('textarea',mark));
                var dst;

                if(!reg.test(target.select.text)) {
                    var url=prompt('Enter your Image URL :');
                    if(!url) return target.container;
                    if(target.select.text=='') target.select.text="Enter your image description here:";
                    dst = replace(target.container, '![ ' + target.select.text + ' ](' + url + ')');
                }
                else dst=replace(target.container,target.select.text.split('[')[1].split(']')[0]);
                return dst;
            },
            list:function(){
                var target=get($('textarea',mark));
                if(target.select.text=='') target.select.text='list text here';
                var dst=replace(target.container,'- '+target.select.text);
                return dst;
            },
            orderlist:function(){
                var target=get($('textarea',mark));
                if(target.select.text=='') target.select.text='list text here';
                var dst=replace(target.container,'1. '+target.select.text);
                return dst;
            },
            code:function(){
                var reg=/^`[^\0]*`$/m;
                var target=get($('textarea',mark));
                var dst;
                if(target.select.text=='') target.select.text='list text here';
                if(!reg.test(target.select.text))
                    dst=replace(target.container,'`'+target.select.text+'`');
                else dst=replace(target.container,target.select.text.split('`')[1]);
                return dst;
            },
            block:function(){
                var target=get($('textarea',mark));
                if(target.select.text=='') target.select.text='quote here';
                var dst=replace(target.container,'> '+target.select.text);
                return dst;
            },
            tab:function(){
                var target=get($('textarea',mark));
                var dst=replace(target.container,'    ' +target.select.text.split('\n').join('\n    '),false);
                return dst;
            },
            preview:function(event){

                var blank=$('.suPreview',mark);
                var state=blank.css('display');
                var target=get($('textarea',mark));
                if(state=='none'){

                    blank.html(marked(target.container.val()));
                    $('pre code',blank).each(function(i,block){
                        hljs.highlightBlock(block);
                    });
                    $('.suEditor',mark).css('display','none');
                    blank.css('display','block');
                }
                else{
                    blank.css('display','none');
                    $('.suEditor',mark).css('display','block');
                    target.container.focus();
                }

            }
        };

        //set the hotkey
        $('textarea',mark).attr('data-state','0').on('keyup',function(event){
            if(event.keyCode==17) $(this).attr('data-state','0');
        });
        $('textarea:input',mark).on('keydown',function(event){
            var dst,tmp;
            switch(event.keyCode){
                case 9:
                    event.preventDefault();
                    dst=methods.tab();
                    break;
                case 17:
                    $(this).attr('data-state','1');
                    break;
                case 66:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.bold();
                        event.preventDefault();
                    }
                    break;
                case 73:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.italic();
                        event.preventDefault();
                    }
                    break;
                case 71:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.img();
                        event.preventDefault();
                    }
                    break;
                case 72:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.head();
                        event.preventDefault();
                    }
                    break;
                case 75:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.code();
                        event.preventDefault();
                    }
                    break;
                case 76:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.link();
                        event.preventDefault();
                    }
                    break;
                case 79:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.orderlist();
                        event.preventDefault();
                    }
                    break;
                case 81:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.block();
                        event.preventDefault();
                    }
                    break;
                case 85:
                    tmp=$(this).attr('data-state');
                    if(tmp=='1') {
                        methods.list();
                        event.preventDefault();
                    }
                    break;

                default :   break;
            }
        });

        $('.su-toolbar > *',mark).each(function(i,element){
            var str=$(this).attr('class');
            str=str.split(/\s+/g);
            for(var i in str){
                if(str[i].substring(0,8)=='su-tool-'){
                    var method=str[i].substring(8);
                    if(method=='preview') break;
                    $(this).on('click',function(event){
                        event.preventDefault();
                        var dst=methods[method]();
                        if(option.preview)
                            $('.suPreview',mark).html(marked(dst.val()));
                        dst.focus();
                    });
                }
            }
            return true;
        });


        if(!option.clean){
            $('.suPreview',mark).css({
                "width":"50%",
                "float":"left",
                'overflow': 'auto',
                'padding': '0 20px',
            });
            $('.suEditor',mark).css({
                "width":"50%",
                "float":"left",
                "display":"block"
            });
            $('textarea',mark).css({
                "tab-size":"4",
                "padding":"20px",
                "resize":"none",
                "overflow":"auto"
            });
            $('textarea:focus',mark).css('background','#fff');
        }
        if(option.preview){
            $('.su-tool-preview',mark).attr('disabled','true');
            $('.suEditor textarea',mark).on('keyup',function(){
                $('.suPreview',mark).html(marked($(this).val()));
                $('pre code',mark).each(function(i,block){
                    hljs.highlightBlock(block);
                });
            });
        }
        else{
            $('.suPreview',mark).css({
                'width':"100%",
                'display':'none',
                "border-left":"solid 1px",
                "border-color": $('textarea',mark).css('border-color')
            });
            $('.suEditor',mark).css({width:"100%"});
            $('.su-tool-preview',mark).on('click',function(){
                methods.preview();
            });
        }




        if(option.css) mark.css(option.css);
        if(option.textCss) $('.textarea',mark).css(option.textCss);
        if(option.previewCss) $('.suPreview',mark).css(option.previewCss);
        if(option.textHeight){
            $('textarea',mark).css('height',option.textHeight);
            $('.suPreview',mark).css('height',option.textHeight);
        }




    };

    if(!option.target) option.target=$('.suMarkdown');

    if(!option.insert){

        var mark=option.target;
        console.log($('textarea',mark).css('height'));
        load(option.target);
    }
    else{
        $.get(option.baseUrl,function(data){
            mark=$("<div></div>");
            mark.html(data);
            load(mark);
            $(option.target).append(mark);
        },'html');
    }


};

