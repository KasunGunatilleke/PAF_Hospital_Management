$(document).ready(function() {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    // Form validation-------------------
    var status = validateHospitalForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If valid------------------------
    $("#formHospital").submit();
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
    $("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
    $("#hosName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#hosTelNo").val($(this).closest("tr").find('td:eq(1)').text());
    $("#hosAddress").val($(this).closest("tr").find('td:eq(2)').text());
    $("#hosEmail").val($(this).closest("tr").find('td:eq(3)').text());
});
// CLIENTMODEL=========================================================================
function validateHospitalForm() {
    // CODE
    if ($("#hosName").val().trim() == "") {
        return "Insert Hospital name.";
    }
    // NAME
    if ($("#hosTelNo").val().trim() == "") {
        return "Insert Hospital Contact Number.";
    }
    // PRICE-------------------------------

    if ($("#hosAddress").val().trim() == "") {
        return "Insert Hospital Address.";
    }

    if ($("#hosEmail").val().trim() == "") {
        return "Insert Hospital email.";
    }
    return true;
}