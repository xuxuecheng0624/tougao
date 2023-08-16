$(function(){
	jQuery(".indexSlideBox").slide({mainCell:".bd ul",autoPlay:true,effect:"leftLoop",trigger:"click",interTime:4500});
	
	jQuery(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"leftLoop",autoPlay:true,vis:3,interTime:4500});
    
    jQuery(".fzgSlideBox").slide({mainCell:".bd ul",autoPlay:true,effect:"leftLoop",trigger:"click",interTime:4500});
    
    jQuery(".qkSlideBox").slide({mainCell:".bd ul",autoPlay:true,effect:"leftLoop",trigger:"click",interTime:4500});

    jQuery(".fzyySlideBox").slide({mainCell:".bd ul",autoPlay:true,trigger:"click",interTime:4500});
    
    jQuery(".zySlideBox").slide({mainCell:".bd ul",autoPlay:true,effect:"leftLoop",trigger:"click",interTime:4500});

    $('.fzYwBox .indexBaseList li:last-child').css('background','none');
    
    
    indexBaseListEach()
    function indexBaseListEach(){
    	 $('.indexBaseList li').each(function(){
	    	 var thisAh = $(this).find('a').height();
	    	 if(thisAh>17){
	    	 	$(this).find('a').css('display','block');
	    	 	$(this).find('a img').css('right','0px')
	    	 }
	    })
    }
    
    
    $('.fzIndexTitleBox ul li').click(function(){
    	$(this).addClass('active');
    	$(this).siblings('li').removeClass('active');
    	$(this).parents('.baseListBox').find('.indexBaseListBox').removeClass('active');
    	$(this).parents('.baseListBox').find('.fzIndexTitleRA').removeClass('active');
    	$(this).parents('.baseListBox').find('.fzIndexTitleRA').eq($(this).index()).addClass('active');
    	$(this).parents('.baseListBox').find('.indexBaseListBox').eq($(this).index()).addClass('active');
        indexBaseListEach()
    	var windowW = $(window).width();
        if(IEVersion()==7){
		    if(windowW<1440){
		    	$('.fzIndexTitleBox ul li.active').css('line-height','36px')
		    }else{
		    	$('.fzIndexTitleBox ul li.active').css('line-height','42px')
		    }
	    }
       if(IEVersion()==8){
		    if(windowW<1440){
		    	$('.fzIndexTitleBox ul li.active').css('line-height','36px')
		    }else{
		    	$('.fzIndexTitleBox ul li.active').css('line-height','42px')
		    }
	    }
    })
    
    $('.indexGkLUl li').hover(function(){
    	 $(this).siblings('li').removeClass('active');
    	 $(this).addClass('active');
    	 $(this).parents('.indexGkBox').find('.indexGkRUl li').removeClass('active');
    	 $(this).parents('.indexGkBox').find('.indexGkRUl li').eq($(this).index()).addClass('active')
    })
    $('.fzIndexTitleBox ul li:last-child,.zsNjTabUl li:last-child').css('margin-right','0')
     
    $('.zsNjTabUl li').hover(function(){
    	   $(this).siblings('li').removeClass('active');
	       $(this).addClass('active');
	       $(this).parents('.zsNjTabUl').siblings('.indexBaseList').removeClass('active');
	       $(this).parents('.zsNjTabUl').siblings('.indexBaseList').eq($(this).index()).addClass('active')
           
           //$(this).find('i').stop().animate({ 'bottom' : '30px'},100)
           indexBaseListEach()
    },function(){
    	   //$(this).find('i').stop().animate({ 'bottom' : '-58px'},100)
    })
    
    
    $('.fzZListUl li:nth-child(2n)').css('background','url(images/fz2019IndexZZLiBg_03.png)');
    $('.fzZLiZzBg .fzZLiZzLi:first-child').css('background','url(images/fz2019IndexZZListLi_07.png) left bottom repeat-x');


    
    function mouse(obj,str,e){
		var x = e.offsetX,  y = -e.offsetY;
		var k = Math.round(-obj.innerHeight()/obj.innerWidth()*100)/100;  // 斜率  Math.round(*100)/100
		var x0 = Math.round(obj.innerWidth()/2*100)/100, 
			y0 = Math.round(-obj.innerHeight()/2*100)/100;
		var kx = Math.round((y-y0)/(x-x0)*100)/100; 
		if(isFinite(kx) && kx > k && kx < -k){
			flag = x-x0 > 0 ? 1 : 0;
			guide(flag,obj,str);
		}
		else{
			flag = y-y0 > 0 ? 2 : 3;
			guide(flag,obj,str);
		}
	}
 
	function guide(x,obj,str){
		var top = 0, left = 0;
		var top1 = 0, left1 = 0;
		var objW = obj.innerWidth()+1;
		var objH = obj.innerHeight();
		if(str == '进'){
			switch(x){
				case 0 : top = 0; left = -objW; break;
				case 1 : top = 0; left = objW; break;
				case 2 : top = -objH; left = 0; break;
				case 3 : top = objH; left = 0; break;
			}
			top1 = 0;
			left1 = 0;
		}
		else{
			switch(x){
				case 0 : top1 = 0; left1 = -objW; break;
				case 1 : top1 = 0; left1 = objW; break;
				case 2 : top1 = -objH; left1 = 0; break;
				case 3 : top1 = objH; left1 = 0; break;
			}
			top = 0;
			left = 0;
		}
 
		// stop() 解决鼠标运动过快，动画在事件结束后仍持续执行的问题
		switch(x){
			case 0 : obj.css({top:top+'px',left:left+'px'}).stop().animate({top:top1+'px',left:left1+'px'});
					 break;
			case 1 : obj.css({top:top+'px',left:left+'px'}).stop().animate({top:top1+'px',left:left1+'px'});
					 break;
			case 2 : obj.css({top:top+'px',left:left+'px'}).stop().animate({top:top1+'px',left:left1+'px'});
					 break;
			case 3 : obj.css({top:top+'px',left:left+'px'}).stop().animate({top:top1+'px',left:left1+'px'});
					 break;
		}  
	}



    $('.fzZListUl li').on('mouseenter',function(e){
		var e = window.event || e;
		mouse($(this).find('.fzZLiZzBg'),'进',e);
	})
	$('.fzZListUl li').on('mouseleave',function(e){
		var e = window.event || e;
		mouse($(this).find('.fzZLiZzBg'),'出',e);
	})

   $('.zywzUl li:nth-child(2n+1) a').css('padding-right','25px')
   
   
   $('.fzBCZWzT ul li:first-child a').css({'padding-left':'0'});
   $('.fzBCZWzT ul li:last-child a').css({'border-right':'0','padding-right':'0'});


   
   imgEnlarge('.zySlideBox .bd ul li a img',1.1,300,'');
   imgEnlarge('.fzyySlideBox .bd li',1.1,300,'parent');
   imgEnlarge('.qkSlideBox .bd img',1.1,300,'');
   imgEnlarge('.fzgSlideBox .bd li',1.1,300,'parent');
   imgEnlarge('.indexSlideBox .bd ul li img',1.1,300,'');
   imgEnlarge('.picScroll-left .bd ul li .pic img',1.1,300,'');
   imgEnlarge('.fzZxfwTopUl li a img',1.1,300,'');
   imgEnlarge('.fzZxfwBottomUl li',1.1,300,'parent');
   function imgEnlarge(name,multiple,time,type){//图片放大
   	   var ltMultiple = (multiple-1)/2;
   	   $(name).hover(function(){
         var imgW = parseInt($(this).width())*multiple+'px';
         var imgH = parseInt($(this).height())*multiple+'px';
         var imgL = parseInt($(this).width())*-ltMultiple+'px';
         var imgT = parseInt($(this).height())*-ltMultiple+'px';
         if(type =='parent'){
         	if(name == '.fzyySlideBox .bd li'){
         		$(this).find('.fzyyImgBox img').css('position','relative');
                $(this).find('.fzyyImgBox img').stop().animate({ 'width' : imgW , 'height' : imgH,'left':imgL,'top':imgT},time)
         	}else{
         		$(this).find('img').css('position','relative');
                $(this).find('img').stop().animate({ 'width' : imgW , 'height' : imgH,'left':imgL,'top':imgT},time)
         	}
         	 
         }else{
         	 $(this).css('position','relative');
             $(this).stop().animate({ 'width' : imgW , 'height' : imgH,'left':imgL,'top':imgT},time)
         }
        
	    },function(){
	    	if(type =='parent'){
		    	if(name == '.fzyySlideBox .bd li'){
		    		$(this).find('.fzyyImgBox img').stop().animate({ 'width' : '100%' , 'height' : '100%','left':0,'top':0},time)
	         	}else{
	    		   $(this).find('img').stop().animate({ 'width' : '100%' , 'height' : '100%','left':0,'top':0},time)
	    	    }
         	}else{
	    		$(this).stop().animate({ 'width' : '100%' , 'height' : '100%','left':0,'top':0},time)
	    	}
	    	
	    })
   }
    

     //判断是否是IE浏览器 
function IEVersion() {
            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
            var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
            var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
            if(isIE) {
                var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
                reIE.test(userAgent);
                var fIEVersion = parseFloat(RegExp["$1"]);
                if(fIEVersion == 7) {
                    return 7;
                } else if(fIEVersion == 8) {
                    return 8;
                } else if(fIEVersion == 9) {
                    return 9;
                } else if(fIEVersion == 10) {
                    return 10;
                } else {
                    return 6;//IE版本<=7
                }   
            } else if(isEdge) {
                return 'edge';//edge
            } else if(isIE11) {
                return 11; //IE11  
            }else{
                return -1;//不是ie浏览器
            }
        }
    function dynamicLoadCss(url) {//加载css方法
            var head = document.getElementsByTagName('head')[0];
            var link = document.createElement('link');
            link.type = 'text/css';
            link.rel = 'stylesheet';
            link.href = url;
            head.appendChild(link);
        }
    
    function dynamicLoadJs(url, callback) {//加载js方法
            var head = document.getElementsByTagName('head')[0];
            var script = document.createElement('script');
            script.type = 'text/javascript';
            script.src = url;
            if (typeof(callback) == 'function') {
                script.onload = script.onreadystatechange = function () {
                    if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
                        callback();
                        script.onload = script.onreadystatechange = null;
                    }
                };
            }
            head.appendChild(script);
        }
  function ieCss(){
  	
    if(IEVersion()==7){
    	var windowW = $(window).width();
	    if(windowW<1440){
	    	$('.fzIndexTitleBox ul li.active').css('line-height','36px')
	    	$('.indexBaseList li').css('padding','4px 0 4px');
	    	$('.fzYwBox .indexBaseList li').css('padding','3px 0 4px');
	    }else{
	    	$('.indexBaseList li').css('padding','7px 0 8px');
	    	$('.fzYwBox .indexBaseList li').css('padding','6px 0 7px');
	    	$('.fzIndexTitleBox ul li.active').css('line-height','42px')
	    }
	    
    }

	   if(IEVersion()==8){
	    	var windowW = $(window).width();
		    if(windowW<1440){
		    	$('.fzIndexTitleBox ul li.active').css('line-height','36px')
		    }else{
		    	$('.fzIndexTitleBox ul li.active').css('line-height','42px')
		    }
	    }
	if(IEVersion()!=-1){
		var windowW = $(window).width();
		    if(windowW<1440){
		    	$('.indexSlideBox .hd ul li').css('line-height','30px')
		    }else{
		    	$('.indexSlideBox .hd ul li').css('line-height','34px')
		    }
		
	}
 }
  
    ieCss()
    $(window).resize(function () {
    	ieCss()
    })
})
