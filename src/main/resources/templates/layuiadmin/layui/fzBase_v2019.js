$(function(){
	$('.fzTCDropDown').hover(function(){
   	    $(this).find('ul').show()
   },function(){
   	    $(this).find('ul').hide()
   })
	
	$('.fzRightNav').click(function(){
    	  $(this).animate({ 'right' : '-56px' },500,function(){
    	  	  $('.fzRightNavBox').animate({ 'right' : '0'},500)
    	  })
    })
    $('.fzRightNavTitle1 img').click(function(){
    	  $('.fzRightNavBox').animate({ 'right' : '-430px' },500,function(){
    	  	  $('.fzRightNav').animate({ 'right' : '0'},500)
    	  })
    })
    
    $('.fzZListUl li .fzZLiZzLi:nth-child(2)').css('background','none')
    
   	    $('.fzRightNavCBox').masonry({
	       itemSelector :'.fzRightNavLi'
	     });
})
