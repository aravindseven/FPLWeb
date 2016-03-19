function Util(){
}

Util.prototype.list = function (el) {
    var s = "";
    var f = "";
    var j = 0;
    if (typeof el == "array"){
        for (var i=0; i<el.length; i++) {
            s += i + "=" + el[i];
        }
    }
    if (typeof el == "object") {
        for (var i in el) {
            try {
                if (el[i] && el[i] != el) {
                    if (("" + el[i]).indexOf("function") == 0) {
                        f += i + "\n";
                    } else {
                        if (typeof el[i] == "object" && el[i] != el.parentNode) {
                            s += i + "={{" + el[i] + "}};\n";
                        }
                        s += i + "=" + el[i] + ";\n";
                        j++;
                    }
                }
            } catch(e) {
                s += "" + i + "\n";
            }
        }
    } else {
        s += el;
    }
    return s + "\n\n-----Functions------\n\n" + f;
};

Util.prototype.encrypt = function(msg){
	 this.salt = CryptoJS.lib.WordArray.random(128/8); 
     this.key256Bits500Iterations = CryptoJS.PBKDF2("Secret Passphrase", this.salt, { keySize: 256/32, iterations: 500 });
     this.iv  = CryptoJS.enc.Hex.parse('101112131415161718191a1b1c1d1e1f'); // just chosen for an example, usually random as well

     var encrypted = CryptoJS.AES.encrypt(msg, this.key256Bits500Iterations, { iv: this.iv });  
     var data_base64 = encrypted.ciphertext.toString(CryptoJS.enc.Base64); 
     var iv_base64   = encrypted.iv.toString(CryptoJS.enc.Base64);       
     var key_base64  = encrypted.key.toString(CryptoJS.enc.Base64);
	 
	 return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
	 
	 /*var passphrase = 'replace-this-with-user-input';
	 return CryptoJS.AES.encrypt(msg, passphrase).ciphertext.toString(CryptoJS.enc.Base64);*/
}

Util.prototype.decrypt = function(cipherText){
      /*var cipherParams = CryptoJS.lib.CipherParams.create({
		ciphertext: CryptoJS.enc.Base64.parse(cipherText)
	  });
	  var decrypted = CryptoJS.AES.decrypt(
		  cipherParams,
		  this.key256Bits500Iterations,
		  { iv: CryptoJS.enc.Hex.parse(this.iv) });
	  return decrypted.toString(CryptoJS.enc.Utf8);*/
	 /*var passphrase = 'replace-this-with-user-input'; 
	 return CryptoJS.AES.decrypt(code, passphrase).toString(CryptoJS.enc.Utf8);*/
}

/*Util.prototype.notify = function(header, message, type, el){
	if(typeof el != "undefined") el.modal('hide');
	$("#notify-" + type).html("<h4>" + this.encode(header) + "</h4><p>" + this.encode(message) + "</p>");
	$("#notify-" + type).show().delay(5000).fadeOut();
}*/

Util.prototype.notify = function(header, message, type, el){
	if(typeof el != "undefined") el.modal('hide');
	$("#notify-" + type + "-info", $("#infoModal")).html("<h1>" + header + "</h1><p>" + message + "</p>");
	$("#notify-" + type + "-info", $("#infoModal")).show();
	$("#infoModal").modal("show");
}

Util.prototype.encode = function(str){
	return (typeof str != undefined) ? str : ""; 
}

Util.prototype.block = function(msg){
	try{
		$.msg({ 
			content : '<img src="support/images/loading.GIF"/> <b>' + msg + '</b>',
			autoUnblock : false
		});
	} catch(e){}
}
Util.prototype.unblock = function(msg){
	try{
		$.msg('unblock');
		console.log("adsf");
		window.setTimeout(function(){$( "#jquery-msg-overlay" ).remove();},100);
	} catch(e){}
}

Util.prototype.gup = function(name){
	  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	  var regexS = "[\\?&]"+name+"=([^&#]*)";
	  var regex = new RegExp( regexS );
	  var results = regex.exec( window.location.href );
	  //alert(results);
	  if( results == null )
	    return "";
	  else
	    return results[1];
}

window.isFF = function () {
	return /Firefox|Iceweasel|Shiretoko/.test(window.navigator.userAgent);
};
	
window.isChrome = function () {
	return /Chrome/.test(window.navigator.userAgent);
};
	
window.isSafari = function () {
	return /Safari/.test(window.navigator.userAgent);
};
	
window.isOpera = function () {
	return /Opera/.test(window.navigator.userAgent);
};
	
window.isSafariLike = function () {
	return /Konqueror|Safari|KHTML/.test(window.navigator.userAgent);
};