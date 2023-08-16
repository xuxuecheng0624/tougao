$(function(){
	   jQuery(".indexSlideBox").slide({mainCell:".bd ul",autoPlay:true,effect:"leftLoop",trigger:"click",interTime:5000});
	   jQuery(".fzgSlideBox").slide({mainCell:".bd ul",autoPlay:true,effect:"leftLoop",trigger:"click",interTime:5000});
	   jQuery(".innerQkGlUl").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,trigger:"click",effect:"leftLoop",autoPlay:true,scroll:7,vis:7,interTime:5000});
	   
	   //jQuery(".txtMarquee-left").slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",interTime:50,trigger:"click"});
	   windowResize()
	   function windowResize(){
	   	    var windowW = $(window).width();
		    if(windowW<1440){
		    	$('.innerWideList li:nth-child(even)').css('margin-left','13px');
		        $('.innerWideList li:nth-child(odd)').css('margin-right','13px');
		        
		         $('.qkZlBox .fzgSlideBox .bd ul li a').hover(function(){
		      	  $(this).find('.QkSlideASpan').stop().animate({ bottom : 0  });
		       },function(){
		      	  $(this).find('.QkSlideASpan').stop().animate({ bottom : '-174px'  });
		       })
		    }else{
		       $('.innerWideList li:nth-child(even)').css('margin-left','15px');
		       $('.innerWideList li:nth-child(odd)').css('margin-right','15px');
		    
		       $('.qkZlBox .fzgSlideBox .bd ul li a').hover(function(){
		      	  $(this).find('.QkSlideASpan').stop().animate({ bottom : 0  });
		       },function(){
		      	  $(this).find('.QkSlideASpan').stop().animate({ bottom : '-209px'  });
		       })
		       
		    }
		   	var fzgSlideHdW = $('.fzgSlideBox .hd').width();
		    $('.fzgSlideBox .hd').css('margin-left',-fzgSlideHdW/2)
		    
		    var innerQkGlUlHdW = $('.innerQkGlUl .hd').width();
		    $('.innerQkGlUl .hd').css('margin-left',-innerQkGlUlHdW/2)
		    
		    

	   }
	    
     
      $(window).resize(function() {
	      windowResize()
	  });
	  
	  $('.innerLdUlBox li:nth-child(even)').css('float','right');
	  $('.innerQkGlUl .hd ul li').text(' ');
	  
	  
	  $('.tstbUlDropdown').hover(function(){
	  	  $(this).find('.tstbUlDropdownUl').show()
	  },function(){
	  	  $(this).find('.tstbUlDropdownUl').hide()
	  })
      
      $('.tstbUlDropdownUl .tstbUlDropdownLi').click(function(){
      	  $(this).parents('.tstbUlDropdown').find('.tstbUlDropdownInput').text($(this).text())
      })
      
     /* 走进方志 时间轴 开始*/
      
      function txtMarqueeLeft1(){
      	  var windowW = $(window).width();
		    if(windowW<1440){
		    	
		    }else{
		       var widUl = 0;
		       $('.txtMarquee-left .bd ul li').each(function(){
		       	    var thisLen = $(this).text().length;
		       	    $(this).find('p').css('width',thisLen/3*10+'px')
		       	    $(this).css('width',thisLen/3*10+20+'px')
		       	    
		       	    $('.txtMarquee-left .bd ul').css({'width':'99900px'})
		       	    widUl += thisLen/3*10+20;
		       })		       
		       setTimeout(function(){
		       	 $('.txtMarquee-left .bd ul').attr('thiswidth',widUl)
		       },1000)
		      
		    }
      }
            
      txtMarqueeLeft1()
      
      jQuery(".txtMarquee-left").slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",interTime:50,trigger:"click"});
      
      txtMarqueeLeft2()
      var type = 'right';
      $('.txtMarquee-left .hd .next').mousedown(function(){
      	  type = 'right';
      })
       $('.txtMarquee-left .hd .prev').mousedown(function(){
      	  type = 'left';
      })
      setInterval(function(){
      	 var ulLeft =  parseInt($('.txtMarquee-left .bd ul').css('left'))
      	 var thisWidth = $('.txtMarquee-left .bd ul').attr('thiswidth')
      	 thisWidth = thisWidth/2;
      	 if(ulLeft<=-thisWidth && type=='right'){
      	 	$('.txtMarquee-left .bd ul').css('left','0')
      	 }
      	 if((ulLeft>=0||ulLeft<=-thisWidth) && type=='left'){
      	 	$('.txtMarquee-left .bd ul').css('left',-thisWidth)
      	 }
      },5)
      function txtMarqueeLeft2(){
      	 $('.txtMarquee-left .bd ul li:last-child').remove()
		 $('.txtMarquee-left .bd ul li:first-child').remove()
	    var DEFAULT_VERSION = 8.0;  
	    var ua = navigator.userAgent.toLowerCase();  
	    var isIE = ua.indexOf("msie")>-1;  
	    var safariVersion;  
	    if(isIE){  
	    safariVersion =  ua.match(/msie ([\d.]+)/)[1];  
	    }  
	    if(safariVersion <= DEFAULT_VERSION){  
	        if(windowW<1440){
	        	var fontW = 11.5
	        }else{
	        	var fontW = 13
	        }
	    }else{
	    	if(windowW<1440){
	        	var fontW = 8.5
	        }else{
	        	var fontW = 10
	        }
	    }
      	  var windowW = $(window).width();
		    if(windowW<1440){
		    	var widUl = 0;
		       $('.txtMarquee-left .bd ul li').each(function(){
		       	    var thisLen = $(this).text().length;
		       	    $(this).find('p').css('width',thisLen/3*fontW+'px')
		       	    $(this).css('width',thisLen/3*fontW+20+'px')
		       	    
		       	    $('.txtMarquee-left .bd ul').css({'width':'99900px'})
		       	    widUl += thisLen/3*fontW+20;
		       	    
		       	    $(this).find('span').css('margin-left',parseInt($(this).find('span').width())*-1/2+'px')
		       })		       
		       setTimeout(function(){
		        	$('.txtMarquee-left .bd ul').attr('thiswidth',widUl)
		       },1000)
		    }else{
		       var widUl = 0;
		       $('.txtMarquee-left .bd ul li').each(function(){
		       	    var thisLen = $(this).text().length;
		       	    $(this).find('p').css('width',thisLen/3*fontW+'px')
		       	    $(this).css('width',thisLen/3*fontW+20+'px')
		       	    
		       	    $('.txtMarquee-left .bd ul').css({'width':'99900px'})
		       	    widUl += thisLen/3*fontW+20;
		       })		       
		       setTimeout(function(){
		        	$('.txtMarquee-left .bd ul').attr('thiswidth',widUl)
		       },1000)
		      
		    }
		 $('.txtMarquee-left .bd ul').css('left','0');
		 $('.txtMarquee-left .bd ul li').removeClass('zjfzTBoxBLi').removeClass('zjfzTBoxTLi')
		
		 $('.txtMarquee-left .bd ul li:nth-child(odd)').addClass('zjfzTBoxTLi')
		 $('.txtMarquee-left .bd ul li:nth-child(even)').addClass('zjfzTBoxBLi')
		 var liLength = ($('.txtMarquee-left .bd ul li').length)/2;
		 if(liLength%2 !=0){
		 	for(var i=liLength;i < liLength*2;i++){
		 		$('.txtMarquee-left .bd ul li').eq(i).removeClass('zjfzTBoxBLi').removeClass('zjfzTBoxTLi');
		 		if(i%2 ==0){
		 			$('.txtMarquee-left .bd ul li').eq(i).addClass('zjfzTBoxBLi')
		 		}else{
		 			$('.txtMarquee-left .bd ul li').eq(i).addClass('zjfzTBoxTLi')
		 		}
		 	}
		 }
      }
    /* 走进方志 时间轴 结束*/  
    jQuery(".zjfzLdghBox").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"leftLoop",autoPlay:true,vis:6,interTime:5000});
      
      
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
    
    if(IEVersion()!=-1){
    	var windowW = $(window).width();
	    if(windowW>=1440){
	    	$('.tpscBtnBox a').css({'padding-top':'14px','padding-bottom':'10px'})
	    	$('.zxlyCTitle a').css({'line-height':'49px'})
	        $('.jssmPhotoBox a').css({'line-height':'41px'})
	        $('.jgszInnerBox li a').css({'line-height':'58px'})
	         $('.innerLdTitleBox a').css({'background':'url(images/jgLdInnerBg1000_2_23.png) no-repeat 37px 11px'})
	         $('.innerWideList li span').css('background','url(images/fz2019Index1200_37.png) left 15px no-repeat')
	    }else{
	    	$('.tpscBtnBox a').css({'padding-top':'12px','padding-bottom':'8px'})
	        $('.zxlyCTitle a').css({'line-height':'40px'})
	        $('.jssmPhotoBox a').css({'line-height':'32px'})
	        $('.jgszInnerBox li a').css({'line-height':'49px'})
	        $('.innerLdTitleBox a').css({'background':'url(images/jgLdInnerBg1000_2_23.png) no-repeat 32px 10px'})
	        $('.innerWideList li span').css('background','url(images/fz2019Index1000_5_03.png) left 12px no-repeat')
	    }
    }
    
    $(window).resize(function () {
    	  if(IEVersion()!=-1){
		    	var windowW = $(window).width();
			    if(windowW>=1440){
			    	$('.tpscBtnBox a').css({'padding-top':'14px','padding-bottom':'10px'})
			    	$('.zxlyCTitle a').css({'line-height':'49px'})
			        $('.jssmPhotoBox a').css({'line-height':'41px'})
			        $('.jgszInnerBox li a').css({'line-height':'58px'})
			         $('.innerLdTitleBox a').css({'background':'url(images/jgLdInnerBg1000_2_23.png) no-repeat 37px 11px'})
			       $('.innerWideList li span').css('background','url(images/fz2019Index1200_37.png) left 15px no-repeat')
			    }else{
			    	$('.tpscBtnBox a').css({'padding-top':'12px','padding-bottom':'8px'})
			        $('.zxlyCTitle a').css({'line-height':'40px'})
			        $('.jssmPhotoBox a').css({'line-height':'32px'})
			        $('.jgszInnerBox li a').css({'line-height':'49px'})
			        $('.innerLdTitleBox a').css({'background':'url(images/jgLdInnerBg1000_2_23.png) no-repeat 32px 10px'})
			        $('.innerWideList li span').css('background','url(images/fz2019Index1000_5_03.png) left 12px no-repeat')
			    }
		    }
    })
})
