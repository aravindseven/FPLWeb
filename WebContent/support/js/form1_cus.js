	function loader(){
		
		$('#form').load("forms/form2_cus.jsp");
	}
	var day = 1;
	for( day; day <= 31; day++){
		$('.d-num').append('<option value="' + day + '">' + day + '</option>');
	}
	var year = 1965;
	for( year; year <= 2015; year++){
		$('.d-year').append('<option value="' + year + '">' + year + '</option>');
	}
	$(document).ready(function(){
		
	$('.bt-cont').click(function(){
		
		$('html,body').animate({scrollTop : $('.formsection').offset().top},500);
	});
	
	$('#nric,#nric1').click(function(){
		$('.qmark').css({'visibility':'visible'});
	});
	$('#none,#none1').click(function(){
		document.getElementById('i-proof').disabled = true;
		$('.qmark').css({'visibility' : 'hidden'});
	});
	$('option:not(#none,#none1)').click(function(){
//		$('.i-proof,.i-proof1').attr("disabled",false);
		$('.i-proof,.i-proof1').prop('disabled','enabled');
		$('.qmark').css({'visibility' : 'visible'});		
	});
	
	$('.qmark').hover(function(){
		$('.ans').addClass('anshov');
	},function(){
		$('.ans').removeClass('anshov');
	});
	});
	
