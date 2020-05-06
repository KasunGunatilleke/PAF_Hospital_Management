$(document).ready(function()
{
    if ($("#alertSuccess").text().trim() == "")
    {
        $("#alertSuccess").hide();
    }
    
    $("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation-------------------
    var status = validateHospitalForm();
    
    if (status != true)
    {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    
    // If valid------------------------
    var method = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";
    
    $.ajax(
    {
        url : "HospitalAPI",
        type : method,
        data : $("#formHospital").serialize(),
        dataType : "text",
        complete : function(response, status)
        {
            onHospitalSaveComplete(response.responseText, status);
        }
    });
});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
    $("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
    $("#hosName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#hosTelNo").val($(this).closest("tr").find('td:eq(1)').text());
    $("#hosAddress").val($(this).closest("tr").find('td:eq(2)').text());
    $("#hosEmail").val($(this).closest("tr").find('td:eq(3)').text());
});

function onHospitalSaveComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } 
        else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } 
    else if (status == "error")
    {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } 
    else
    {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    
    $("#hidHospitalIDSave").val("");
    $("#formHospital")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
    {
        url : "HospitalAPI",
        type : "DELETE",
        data : "hosID=" + $(this).data("hospitalid"),
        dataType : "text",
        complete : function(response, status)
        {
            onHospitalDeleteComplete(response.responseText, status);
        }
    });
});

function onHospitalDeleteComplete(response, status)
{
    if (status == "success")
    {
        var resultSet = JSON.parse(response);
        
        if (resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } 
        else if (resultSet.status.trim() == "error")
        {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } 
    else if (status == "error")
    {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } 
    else
    {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}


function validateHospitalForm()
{
	//Hospital  NAME---------------------------
    if ($("#hosName").val().trim() == "")
    {
        return "Insert Hospital Name.";
    }
    
    // Phone Number-------------------------------
    if ($("#hosTelNo").val().trim() == "")
    {
        return "Insert Hospital contact Number.";
    }
    
    // Check for numeric value
	var phone = $("#hosTelNo").val().trim();
	if (!$.isNumeric(phone)) {
		return "Insert a correct conatct number (don't insert characters)";
	}
	
	// check for length
	var pattern = /^\d{10}$/;
	if (!pattern.test(phone)) {
		return "Contact number should have 10 numbers";
	}
    
    //Address-------------------------------
    if ($("#hosAddress").val().trim() == "")
    {
        return "Insert Hospital Address.";
    }
    
    // Email------------------------
    
    if ($("#hosEmail").val().trim() == "") {
		return "Insert Email.";
	}

	var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var email = $("#hosEmail").val().trim();
	if (re.test(email) == false) {
		return "Please enter valid email address";
	}
      
    return true;
}