<head><link type="text/css" rel="stylesheet" href="css/form2_cus.css">
</head>
<style type="text/css">

.img{
	background: url(images/inputtop.png) no-repeat #FFF;
	background-position: right top;
	background-size: 20px;
}
</style>
<body>
		<div class="f-indicator">
        	<img src="images/form1_1.png" onClick="load1();" style="cursor: pointer;">
            <img src="images/form2_2.png" style="cursor: pointer;">
            <img src="images/form1_3.jpg">
        </div>
        <form method="post" class="identify" name="identify">
        	<table id="tab2">
               
                <tr>
	                <td><input type="text" name="postal" placeholder="Postal Code" value="" class="i-input post img" required></td>
                    <td><input type="text" name="unit" placeholder="Unit Number" value="" class="i-input unum img"></td>                	
                </tr>
                <tr>
                    <td><input type="text" name="block" placeholder="Block Number" value="" class="i-input blkn"></td>            	             
                    <td><input type="text" name="building" placeholder="Building Name" value="" class="i-input bldn"></td>
                </tr>
                <tr>
                    <td><input type="text" name="street" placeholder="Street" class="i-input" value=""></td>
                     <td><input type="text" name="country" placeholder="Country" value="Singapore" class="i-input "> </td>
                    
                </tr>
               
                <tr>
                   <td><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;"><input style="width:78%;margin-left:2px;" type="text" name="mobnum" placeholder="Mobile Number" value="" class="i-input mobnum img" required></td>
                    <td><input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;"><input type="text" name="landline" placeholder="Landline Number" value="" class="i-input llnum" required style="width:78%;margin-left:2px;"></td>
                </tr>     
            </table>
              <input type="button" name="bt-continue" value="Continue" class="bt-end bt-cont bt-form2" onClick="loader();">
               <input type="button" name="bt-cancel" value="Back" class="bt-end bt-cancel" onClick="goback();">
                <input type="button" name="bt-cancel" value="Cancel" class="bt-end bt-cancel">
        </form>
<script src="js/form2_cus.js"></script>
</body>

