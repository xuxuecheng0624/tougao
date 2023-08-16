
    	$(function(){
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
		    
		        var windowW = $(window).width();
			    if(IEVersion()==7||IEVersion()==8){
			    	if(windowW<1440){
				    	$('.cj_tr_title span').css('line-height','28px')
				    }else{
				    	$('.cj_tr_title span').css('line-height','30px')
				    }
			    	
			    }
			    if(IEVersion()!=-1){
			    	if(windowW>=1440){
				    	$('.slideTxtBox .hd ul li').css('line-height','58px')
				    }else{
				    	$('.slideTxtBox .hd ul li').css('line-height','48px')
				    }
			    	
			    }
			    
			    
			$(window).resize(function () {
				var windowW = $(window).width();
			    if(IEVersion()==7||IEVersion()==8){
			    	if(windowW<1440){
				    	$('.cj_tr_title span').css('line-height','28px')
				    }else{
				    	$('.cj_tr_title span').css('line-height','30px')
				    }
			    	
			    }
			    if(IEVersion()!=-1){
			    	if(windowW>=1440){
				    	$('.slideTxtBox .hd ul li').css('line-height','58px')
				    }else{
				    	$('.slideTxtBox .hd ul li').css('line-height','48px')
				    }
			    	
			    }
			})
			    
			    
    	})