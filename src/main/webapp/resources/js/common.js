var ajax = new Ajax();

function Ajax(){

	this.formSerialize = function(frm){
	    return $(frm).serialize();
	};

	this.callAjax = function (url,method,formData,callbackFunc,resDataType,async){
		var tokenName = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
				headers: {
					tokenName : $("meta[name='_csrf']").attr("content"),
			    },
				url : url, 
				type : method,
				data : formData,
				dataType : resDataType,
				async : async,
				contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data){
					callbackFunc(data);
				},
			    error : function(data){
					alert("통신실패\n재시도해 해주세요.");
					location.reload();
				}
		});
	};
}

function menuLink(url){
	$("#commonForm").attr({
		"action":url
		,"method":"post"
	}).submit();
}
function logout(){
	ajax.callAjax("/login/logout.dw","post", ajax.formSerialize($("#commonForm")),
			logoutCallback,"json",false);
}

function logoutCallback(data){
	$("#commonForm").attr({
		"action":"/login/login.dw"
		,"method":"post"
	}).submit();	
}

