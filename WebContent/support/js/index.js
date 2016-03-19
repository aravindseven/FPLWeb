$(document).ready(function(){
	
	/* Opening the forms
	   Check what form is on top using 'topform' class
	 */	
	$('.toreg').click(function(){
		if( $('.register').hasClass('r-hide')){
			if( ($('.b-form').hasClass('topform'))){
				$('.b-form').addClass('b-f-hide');		
				$('.b-form').removeClass('topform');
				$('.register').addClass('topform');
			}else{
				$('.register').addClass('topform');	
			}
		/* Scroll the form into viewport */
		if ( $(this).hasClass('m-toreg') )
			$('html,body').animate({scrollTop : $('.register').offset().top}, 1000);
		$('.register').removeClass('r-hide');
		$('.dropshadow').removeClass('d-hide');
		}
		else{}
		
	});
	/* Popup of the forgot password form */
	$('.getpwd').click(function(){
		$('.fgtpwd').removeClass('fp-hide');
	});
	$('.fgtpwd p').click(function(){
		$('.fgtpwd').addClass('fp-hide');
	});
	/* removes the dropshadow and any forms that is active */
	$('.dropshadow,.r-closer').click(function(){
		if( !($('.dropshadow').hasClass('d-hide'))){
			if($('.register').hasClass('topform')){
				$('.register').addClass('r-hide');
				$('.dropshadow').addClass('d-hide');
				$('.register').removeClass('topform');
				$('.role').removeClass('role-hide');
				$('.custform').addClass('form-hide');
				$('.orgform').addClass('orgform-hide');
			}
			else if( $('.b-form').hasClass('topform') ){
				 $('.b-form').addClass('b-f-hide').removeClass('topform');
				 $('.fgtpwd').addClass('fp-hide');
				 $('.dropshadow').addClass('d-hide');
			}
		}
	});
    $('.b-opener').click(function(){
		if( $('.register').hasClass('topform')){
			$('.register').addClass('r-hide').removeClass('topform');
			$('.b-form').addClass('topform');
		}
		else{
			$('.b-form').addClass('topform');	
		}
		if ( $(this).hasClass('m-opener') )
			$('html,body').animate({scrollTop : $('.b-form').offset().top}, 1000);
        $('.b-form').removeClass('b-f-hide');
		$('.dropshadow').removeClass('d-hide');
    });
	
    $('.b-closer').click(function(){
        $('.b-form').addClass('b-f-hide');
	    $('.dropshadow').addClass('d-hide');
    });
	
	
	
	
	/* Code for banner image changes */	
	var count = 1;
	setInterval(function(){
		count++;
		if(count>3)
			count = 1;
		$('.banner img').attr('src','support/images/banner' + count + '.jpg');
	},3000);
	
	/* Mobile menu popup */
	
	var menu = false;
	$('.mobmenuicon').click(function(){
		if( menu == true ){
		$('.container').removeClass('container-pushdown');
		$('.mobilemenu').addClass('mm-hide');
		$('.mobilemenu div ul').addClass('menu-rotup');
		menu = false;

		}
		else{
		$('.container').addClass('container-pushdown');
		$('.mobilemenu').removeClass('mm-hide');
		$('.mobilemenu div ul').removeClass('menu-rotup');
		menu = true;
		}
	});
	
	$('.mobilemenu p').click(function(){
		$('.container').removeClass('container-pushdown');
		$('.mobilemenu').addClass('mm-hide');
		$('.mobilemenu div ul').addClass('menu-rotup');
		menu = false;
	});
	
	/* custom radio button */

	var $ind = $('.f-t-ind'),
		$cor = $('.f-t-cor'),
		indsel = false,
		corsel = false;
	$('.f-t-ind').click(function(){

			if( indsel == false ){
				$('.f-t-ind .inner').addClass('selected');
				$('.f-t-cor .inner').removeClass('selected');
				indsel = true;
				corsel = false;
			}
	});
	$('.f-t-cor').click(function(){
			if( corsel == false ){
				$('.f-t-ind .inner').removeClass('selected');
				$('.f-t-cor .inner').addClass('selected');
				indsel = false;
				corsel = true;
			}

	});


	/* Customer/Financial planner form popup */

	$('.r-cust').click(function(){
		$('.role').addClass('role-hide');
		$('.custform').removeClass('form-hide');
	});
	
	$('.r-finplan').click(function(){
		$('.role').addClass('role-hide');
		$('.orgform').removeClass('orgform-hide');
	});
	
	$('.f-back').click(function(){
		$('.custform').addClass('form-hide');
		$('.orgform').addClass('orgform-hide');
		$('.role').removeClass('role-hide');
	});
	
	$('.f-submit').click(function(){
		alert('hello on form Submit');
		var response = grecaptcha.getResponse();
		if(response.length == 0){
		    alert('Captcha is not verified');
		    return false;
		}		
	});
	$('.f-confirm button').click(function(){
		$('.dropshadow').addClass('d-hide');
		$('.register').addClass('r-hide');
		$('.role').removeClass('role-hide');		
		$('.custform').addClass('form-hide');
		$('.orgform').addClass('orgform-hide');
		$('.f-confirm').addClass('f-c-hide');	
	});

	
	$('.h-country').click(function(){
		$('.h-counlist').toggleClass('h-cl-hide');
	});
	
});


