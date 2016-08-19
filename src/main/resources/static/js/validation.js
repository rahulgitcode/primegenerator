	
	
function validateForm(formId){
	var currentForm = $('form').serialize();
	if(originalForm == currentForm){
		alert("Please update atleast one field");
        return false;
    }else{
    	return true
    }
	
}

function setForm(){
	originalForm = $('form').serialize();
}

function validateLength(validateField,limitLength){
	if(validateField!=""&&(validateField.trim()).length>limitLength){
		return false;
	}else{
		return true;
	}
	
}

function validateNumber(validateField){
	if((validateField.trim()).length>0){
		if((!isNaN(validateField))){
			if (Number(validateField) === parseInt(validateField, 10)){
			    return true;
			}else{
			   return false
		    }
	}
	}else{
		return true;
	}
	
}

var floatExp = /^((\d+(\.\d*)?)|((\d*\.)?\d+))$/;
var DigitExp = /^\d/;
function validateDecimal(validateField){
	if((validateField.trim()).length>0){
		return floatExp.test(validateField);
	}else{
		return true;
	}
	
}
function validateDecimalLength(validateField,maxIntLgth,maxDecLgth)
{
	
   var length;
   var decPosn;
   var intPrtLgth;
   var decPrtLgth;
   var isValid = false;
  
   
   if((validateField.trim()).length>0 && validateDecimal(validateField)) {
      length = validateField.length;
      decPosn = validateField.indexOf(".");
      
      if(decPosn != -1) {
        intPrtLgth = decPosn;
        decPrtLgth = length -(decPosn+1);     
      }
      else if(decPosn==(length-1)){ 
         intPrtLgth = length-1;
         decPrtLgth = 0;
      }
      else{
        intPrtLgth = length;
        decPrtLgth = 0;
      }      
      if( (intPrtLgth < maxIntLgth+1) && (decPrtLgth < maxDecLgth+1)) {
         isValid = true;
       }
      else{
         isValid = false;
      }
         
    }else{
    	return true;
    }
  
    return isValid;
   
   }





