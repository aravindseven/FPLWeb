var openarr = [];
var openarr2 = [];
var openarr3 = [];
var removeItem = '*';
var k = 0;
var opentempobj ={};
var lastelement = {}; 
var lastelement_id ='' ;
var lastelement_id2 = {};
var ifuniqueflagmini = 0;
var lastelementarr = [];
var lastelement_idorg = '';



function isElementVisible(el) {

    var rect     = $('#compose').getBoundingClientRect(),
        vWidth   = window.innerWidth || doc.documentElement.clientWidth,
        vHeight  = window.innerHeight || doc.documentElement.clientHeight,
        efp      = function (x, y) { return document.elementFromPoint(x, y) };     

    // Return false if it's not in the viewport
    if (rect.right < 0 || rect.bottom < 0 
            || rect.left > vWidth || rect.top > vHeight)
        return false;

    // Return true if any of its four corners are visible
    return (
          el.contains(efp(rect.left,  rect.top))
      ||  el.contains(efp(rect.right, rect.top))
      ||  el.contains(efp(rect.right, rect.bottom))
      ||  el.contains(efp(rect.left,  rect.bottom))
    );
}



 $('#closerr1').click(function(){

            $('#chat_contain1').fadeOut();
        });


$('#chat_title1').click(function(){
            $('#chat_contain1').toggleClass('opened');
            if($('#chat_contain1').hasClass('opened'))
            {
            $('#chat_contain1').animate({
                bottom:'21.45em'

            },400);
            }
            else
            {
                $('#chat_contain1').animate({
                bottom:'0'

            });
            }
        });





$('#closerr2').click(function(){

            $('#chat_contain2').fadeOut();
        });


$('#chat_title2').click(function(){
            $('#chat_contain2').toggleClass('opened');
            if($('#chat_contain2').hasClass('opened'))
            {
            $('#chat_contain2').animate({
                bottom:'21.45em'

            },400);
            }
            else
            {
                $('#chat_contain2').animate({
                bottom:'0'

            });
            }
        });



$('#closerr3').click(function(){

            $('#chat_contain3').fadeOut();
        });


$('#chat_title3').click(function(){
            $('#chat_contain3').toggleClass('opened');
            if($('#chat_contain3').hasClass('opened'))
            {
            $('#chat_contain3').animate({
                bottom:'21.45em'

            },400);
            }
            else
            {
                $('#chat_contain3').animate({
                bottom:'0'

            });
            }
        });


$('#closerr4').click(function(){

            $('#chat_contain4').fadeOut();
        });


$('#chat_title4').click(function(){
            $('#chat_contain4').toggleClass('opened');
            if($('#chat_contain4').hasClass('opened'))
            {
            $('#chat_contain4').animate({
                bottom:'21.45em'

            },400);
            }
            else
            {
                $('#chat_contain4').animate({
                bottom:'0'

            });
            }
        });





$('#closerr5').click(function(){

            $('#chat_contain5').fadeOut();
        });


$('#chat_title5').click(function(){
            $('#chat_contain5').toggleClass('opened');
            if($('#chat_contain5').hasClass('opened'))
            {
            $('#chat_contain5').animate({
                bottom:'21.45em'

            },400);
            }
            else
            {
                $('#chat_contain5').animate({
                bottom:'0'

            });
            }
        });

$('#closerr6').click(function(){

            $('#chat_contain6').fadeOut();
        });


$('#chat_title6').click(function(){
            $('#chat_contain6').toggleClass('opened');
            if($('#chat_contain6').hasClass('opened'))
            {
            $('#chat_contain6').animate({
                bottom:'21.45em'

            },400);
            }
            else
            {
                $('#chat_contain6').animate({
                bottom:'0'

            });
            }
        });
 var hidecounter=0;
 var min = [];
function minimizer(){

    var chatterWidth = $('.row.chatter').width();

     
    console.log("chatterWidth" + chatterWidth);  
  
    
     
     //console.log(chatterWidth);

      if($('div').hasClass("chat_contain"))
    {


        var el = $('.chat_contain').filter(function() 
        {
         return $(this).css('display') == 'block';
        });
      
        /*console.log(el);
        $(el).each(function(){
            console.log("Eli--Before");
            console.log(el[0]);
            console.log("Eli After");
            console.log(el[1]);
           });*/
           


       



       
        var widthOfBox = el.length;    
        var widthOfBoxTemp = $('.chat_contain').width();
        var widthOfBox = widthOfBox * widthOfBoxTemp;
        console.log("NO of elements shown" + el.length);
        console.log("Total width covered by boxes" + widthOfBox);
        console.log("Total parent width" + chatterWidth);
        var coverPercent = (widthOfBox / chatterWidth) * 100;
        lastelement = openarr3[openarr3.length-1];
        console.log("latest elemetn");
        console.log(lastelement);
        console.log("Total percent COvered:" + coverPercent);
        var lastelementhash = '#'+lastelement.id;
        var pooss = $(lastelementhash).position().left;
         console.log("Left most position21:-->" + pooss);
        if(coverPercent >= 74.40497193263833)
        {
            $('.overflow_counter').show();
            //var pooss = $(el).last().position().left;
            var pooss = $(lastelementhash).position().left;
           console.log("Total percent COvered:" + coverPercent);
            console.log("Left most position2:-->" + pooss);
            if(pooss <= 296.128125)
            {  /* var tel = el.last() + '.chat-title > uname';
                console.log(tel.last());*/
                console.log(lastelement);
                //$(el).last().hide();
                lastelement_id2 = lastelement;
                lastelement_id =  lastelement.id; 
                console.log("latest elemetn id");
                console.log(lastelement_id);
                lastelement_idorg = lastelement_id;
                lastelement_id = lastelement_id.replace(/\D/g,'');
                var appendText = '#chat_contain';
                lastelement_id = appendText + lastelement_id;

                lastelementarr.push(lastelement_id2);
                console.log("last element array");
                console.log(lastelementarr);
               /* for(i=0;i<lastelementarr.length;i++)
                {
                    if(lastelement_id2.id == lastelementarr[i].id)
                    {
                        ifuniqueflagmini = 0;
                        alert(0);
                    }
                    else
                    {
                        ifuniqueflagmini = 1;
                        alert(1);
                    }
                    console.log("loop rrun");
                    console.log(lastelementarr);
                }*/
                var miniorgcount = 0; 
                for(var l=0;l<lastelementarr.length;l++)
                {
                    if((lastelementarr[l].id) == (lastelement_idorg))
                    {
                        miniorgcount++;
                    }
                }
                if(miniorgcount>1)
                {   
                   /* alert(miniorgcount);
                    alert("repeated");*/
                    
                }
                else
                {
                    hidecounter++;
                    $('ul#mini_populate').append('<li id="'+lastelement_id2.id+'sm" class=\"minipopulateli\"><a href=\"#\">'+lastelement_id2.name+'</a></li>');
                
                }
               
                   
                   
                    console.log(lastelement_id + 'is thelast elements');
                    //if(last)
                    $(lastelement_id).hide();
                    console.log(lastelement_id);
                    
                    
                   
               
                
                $('.overflow_counter').show();
                console.log("overloaded and " + hidecounter + "are hidden");
                $('#minicounterval').text(hidecounter);
            }
            if(hidecounter==0)
            {
                $('.overflow_counter').hide();
            }
            else
            {
                $('.overflow_counter').show();
            }
            
        }
       
       
           
      
    }







    }


$(document).ready(function() {



   


//minimizer();
hidecounter = 0;

 $('#minicounter').click(function(){

        $('#minichat').toggle();
        
       

    });
 $('#minichat').mouseleave(
            function()
            {
                $('#minichat').fadeOut(2000);
            }

            );
 $('#minichat').mouseenter(
            function()
            {
                $('#minichat').fadeIn(2);
            }

            );
 $('#minichat').click(
            function()
            {
                $('#minichat').show();
            }

            );

 




$("li.chat_main_list").click(function(){
        var contentPanelId2 = $(this).attr("id");
        contentPanelId3 = contentPanelId2;
        var contentPanelId2 = "#"+contentPanelId2;
        contentPanelId2 = contentPanelId2+" ul li.licont.bold div";
        console.log(contentPanelId2);
        opentempobj.id = contentPanelId3;
        opentempobj.name = $(contentPanelId2).text(); 
        openarr.push(opentempobj);
        console.log($(contentPanelId2).text());
        console.log(openarr);
        removedupe();
        console.log(openarr);
        opentempobj = {};

    });
$("li.chat_main_list").click(function(){
     if($(this).attr("id"))
     {minimizer();} 
    console.log("minimizer called");
});


      function removedupe() 
      { var count = 0;
        openarr2 = openarr;
        for(var i=0;i<openarr2.length;i++)
        {   console.log(openarr2);
            for(var j=0;j<openarr2.length;j++)
            {
                if((openarr2[i].id == openarr2 [j].id)&&(openarr2[i]!='*'))
                {
                    count ++;
                    if(count>1)
                    {   openarr2[j].id = '*' ;
                        openarr2[j].name = '*';
                        //alert("duplicate");
                    }
                }
            }
            count = 0; 
        }
        openarr3 = openarr2;
        openarr3 = $.grep(openarr3, function(openarr2) {
          return openarr2.id != removeItem;
        });
        
        console.log(openarr3);
      };




    $("li.chat_main_list").click(function(event) {
    var contentPanelId = $(this).attr("id");
    contentPanelId = contentPanelId.replace(/\D/g,'');
    var appendText = '#chat_contain';
    contentPanelId = appendText + contentPanelId;
    $(contentPanelId).show();
    
    });
});

    $(".minipopulateli").click( function(event){
            var contentPanelIdmini = $(this).attr("id");
            contentPanelIdmini = contentPanelIdmini.replace(/\D/g,'');
            var appendText = '#chat_contain';
            contentPanelIdmini = appendText + contentPanelIdmini;
            $(contentPanelIdmini).show();

        });


$(function(){
        $('.chat_contain').attr('style','bottom:21.45em;');
        $('.chat_contain').hide();
        $('.chat_contain').addClass('opened');

    });
// for the profile pic tab
$(document).ready(function(){
    
    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tab-content_profile').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    });

});


$(document).ready(function(){
    $('.overflow_counter').hide();

    function blink(selector){
    $(selector).fadeOut('slow', function(){
        $(this).fadeIn('slow', function(){
            blink(this);
        });
    });
    }
    blink('.blinker');









   


     

   
   

   


   

});

$(window).resize(function(){

    console.log("status");
    console.log(isElementVisible());
    minimizer();

});


// for the profile pic tab

//tooltip settings

//tooltip settings