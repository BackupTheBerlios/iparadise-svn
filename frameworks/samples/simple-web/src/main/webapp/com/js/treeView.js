function manageCheckboxes(checkboxName, parentName) {
    var myForm = document.getElementsByTagName("form")[0];
    var status = (((document.getElementsByName(checkboxName)).item(0)).checked == true);

    if (parentName == 1) {
        for (var i = 1; i < myForm.elements.length; i++) {
            if (myForm.elements[i].id == checkboxName) {
                myForm.elements[i].checked = status;
            }
        }
    } else {
        ((document.getElementsByName(parentName)).item(0)).checked = true;
    }
}