$(document).ready(function(){
	$(window).load(function(){
		$('#form').load("forms/form2_fp_subscription.html");	
	});
	$('.toreg').click(function(){
		if( $('.register').hasClass('r-hide')){
		$('.register').removeClass('r-hide');
		$('.dropshadow').removeClass('d-hide');
		}
		else{}
	});
	$('.dropshadow').click(function(){
		if( !($('.dropshadow').hasClass('d-hide'))){
		$('.register').addClass('r-hide');
		$('.dropshadow').addClass('d-hide');
		}
	});
    $('.b-opener').click(function(){
        $('.b-form').removeClass('b-f-hide');
    });
    $('.b-closer').click(function(){
        $('.b-form').addClass('b-f-hide');
    });
	
	$('.bt-cont').click(function(){
		$('html,body').animate({scrollTop : $('.formsection').offset().top},500);
	});
	
	


	var count = 1;
	setInterval(function(){
		count++;
		if(count>3)
			count = 1;
		$('.banner img').attr('src','images/banner' + count + '.jpg');
	},3000);
	
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
	});


	$('.h-country').click(function(){
		$('.h-counlist').toggleClass('h-cl-hide');
	});



});