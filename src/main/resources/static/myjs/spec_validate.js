$(document).ready(function(){
                //1.hide error Selection
                $("#specCodeError").hide();
                $("#specNameError").hide();
                $("#specNoteError").hide();

                //2.define error variable
                var specCodeError=false;
                var specNameError=false;
                var specNoteError=false;
                //3.define validate function
                //spec code validation
                function validate_specCode(){
                    var val=$("#specCode").val();
                    var exp=/^[A-Z]{4,12}$/;

                    if(val == '')
                    {
                       $("#specCodeError").show();
                       specCodeError=false;  
                       $("#specCodeError").html("<b>Code</b> can not be empty");
                       $("#specCodeError").css('color','red');  
                    }else if(!exp.test(val)){
                        $("#specCodeError").show();
                       specCodeError=false;  
                       $("#specCodeError").html("<b>Code</b> must be 4-12 characters only");
                       $("#specCodeError").css('color','red');  
                    }
                    
                    else{
                        $("#specCodeError").hide();
                        specCodeError=true;
                    }

                    return  specCodeError;
                }
                //spec Name validation
                function validate_specName(){
                    var val=$("#specName").val();
                    var exp=/^[A-Za-z0-9\s\.]{4,25}$/;
                    if(val == '')
                    {
                        $("#specNameError").show();
                        specNameError=false;
                        $("#specNameError").html("<b>Name</b> can not be empty");
                        $("#specNameError").css('color','red');   
                    }
                    else if(!exp.test(val)){
                        $("#specNameError").show();
                        $("#specNameError").html("<b>Name</b> must be 4-12 characters");
                        $("#specNameError").css('color','red');
                    }
                    else{
                        $("#specNameError").hide();
                        specNameError=true;
                    }
                    return specNameError;
                }
                //spec Note validation
                function validate_specNote(){
                    var val=$("#specNote").val();
                    var exp=/^[A-Za-z0-9\s\.\-\,]{10,250}$/;

                    if(val =='')
                    {
                        $("#specNoteError").show();
                        specNoteError=false;
                        $("#specNoteError").html("<b>Note</b> can not be empty");
                        $("#specNoteError").css('color','red');
                    }else if(!exp.test(val)){
                        $("#specNoteError").show();
                        specNoteError=false;
                        $("#specNoteError").html("<b>Note</b> can have [10-250] A-Za-z0-9.,-(space) chars");
                        $("#specNoteError").css('color','red');
                    }
                    else{
                        $("#specNoteError").hide();
                        specNoteError=true;
                    }
                    return specNoteError;
                }

                //4.action event
                $("#specCode").keyup(function(){
                    $(this).val($(this).val().toUpperCase());
                    validate_specCode();
                });

                $("#specName").keyup(function(){
                    validate_specName();
                });
                
                $("#specNote").keyup(function(){
                    validate_specNote();
                });
                
                //5.on submit
                $("#specForm").submit(function(){
                    validate_specCode();
                    validate_specName();
                    validate_specNote();

                    if(specCodeError && specNameError && specNoteError)
                    return true;
                    else return false;
                });
            });