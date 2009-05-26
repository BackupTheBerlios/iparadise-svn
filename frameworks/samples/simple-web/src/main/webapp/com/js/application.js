function HideContent(d) {
    document.getElementById(d).style.display = "none";
}
function ShowContent(d) {
    document.getElementById(d).style.display = "";
}

//Toggle a layer. This will change the image and text of the image too
function ReverseContentDisplay(d, imagePath, showText, hideText) {
    var layoutImg = document.getElementById(d + 'Image');
    var layoutMessage = document.getElementById(d + 'Message');
    if (document.getElementById(d).style.display == "none") {
        document.getElementById(d).style.display = "";
        layoutImg.src = imagePath + '/hide_layer.gif';
        layoutMessage.innerHTML = hideText;
    }
    else {
        document.getElementById(d).style.display = "none";
        layoutImg.src = imagePath + '/show_layer.gif';
        layoutMessage.innerHTML = showText;
    }
}
function PopupPreview(url) {
    popwinPreview = window.open(url, 'popwinPreview', 'width=520,height=300,left=0,top=0,toolbar=No,location=No,scrollbars=Yes,status=No,resizable=Yes,fullscreen=No');
    if (popwinPreview) {
        popwinPreview.focus();
    }
    return false;
}
function LOVPopUp(url, indexId) {

    if (indexId != null) {
        url = url + "&parameterValue=" + indexId;
    }
    window.open(url, 'popup', 'width=720, height=400,left=0,top=0, directories=1, location=1, menubar=1, status=1 toolbar=1, resizable=Yes,scrollbars=Yes');
    return false;
}
function LOVClose(openerField, indexId) {
    //cancel button
    if (openerField == '') {
        window.close();
        return;
    }
    var form = document.getElementsByName('LOVFormBean')[0];
    popupField = 'id';
    var inputField = form[popupField];
    var value = "";
    var name;
    if (inputField.options) {
        value = inputField.options[form[popupField].selectedIndex].value;
    } else if (inputField.type == "file") {
        value = inputField.value;
    } else {
        for (i = 0; i < form.elements.length; i++) {
            var element = form.elements[i];
            if (element.name == popupField && element.checked) {
                value = element.value;
        //Select the hiiden filed value
                name = document.getElementsByName("hid" + element.value)[0].value
                break;
            }
        }
    }
  //no item selected
    if (value == "") {
        alert('Please select an item.');
        return false;
    }

    var openerFieldRef = window.opener.document.forms[0][openerField];
    if (indexId != null) {
        if (openerFieldRef[indexId].type == "select-one") {
            for (i = 0; i < openerFieldRef[indexId].options.length; i++) {
                if (openerFieldRef[indexId].options[i].value == value) {
                    openerFieldRef[indexId].selectedIndex = i;
                    break;
                }
            }
        } else {
            openerFieldRef[indexId].value = value;
        }
    } else {
        if (openerFieldRef.type == "select-one") {
            if (openerFieldRef.options.length == 1) {
                openerFieldRef.options[0].value = value;
                openerFieldRef.options[0].text = name;
            } else {

                for (i = 0; i < openerFieldRef.options.length; i++) {
                    if (openerFieldRef.options[i].value == value) {
                        openerFieldRef.selectedIndex = i;
                        openerFieldRef.focus();
                        break;
                    }
                }
        //                openerFieldRef.options[1] = null;
                //                openerFieldRef.options[0].value = value;
                //                openerFieldRef.options[0].text = name;
            }
        } else {
            openerFieldRef.value = value;
        }
    }
    window.close();
}
function selectRow(tr) {
    alert(tr);
    alert(tr.type);
    alert(tr.typeName);
}
function ConfirmDelete() {
    return confirm('Do you realy want to delete?');
}

function ConfirmAction(msgText) {
    return confirm('Do you realy want to ' + msgText + '?');
}
// Added JS to submit forms from Action button
function submitForm(actionName, formId) {
    document.getElementById(formId).dispatch.value = actionName;
    document.getElementById(formId).submit();
}

function submitFormByEnter(actionName, formId) {
    var myCharCode = window.event.keyCode;
    if (myCharCode == 13) {
        window.event.keyCode = '#0';
        submitForm(actionName, formId);
    }
}

function deleteByCheckBox(actionName, formId) {
    myForm = document.getElementById(formId);
    showConfirmDelete = false;
    for (i = 0; i < myForm.elements.length; i++) {
        var element = myForm.elements[i];
        if (element.checked) {
            showConfirmDelete = true;
            break;
        }
    }

    if (showConfirmDelete == true) {
        if (ConfirmDelete()) {
            document.getElementById(formId).dispatch.value = actionName;
            document.getElementById(formId).submit();
        }
    } else {
        alert('No row selected for deleting.')
    }
}

function actionByCheckBox(actionName, formId, msgText) {
    myForm = document.getElementById(formId);
    showConfirm = false;
    for (i = 0; i < myForm.elements.length; i++) {
        var element = myForm.elements[i];
        if (element.checked) {
            showConfirm = true;
            break;
        }
    }

    if (showConfirm == true) {
        if (ConfirmAction(msgText)) {
            document.getElementById(formId).dispatch.value = actionName;
            document.getElementById(formId).submit();
        }
    } else {
        alert('No row selected to ' + msgText + '.')
    }
}

function forwardToScreen(dispatchName, formId, actionName) {
    myForm = document.getElementById(formId);
    showConfirm = false;
    for (i = 0; i < myForm.elements.length; i++) {
        var element = myForm.elements[i];
        if (element.checked) {
            showConfirm = true;
            break;
        }
    }

    if (showConfirm == true) {
        document.getElementById(formId).dispatch.value = dispatchName;
        document.getElementById(formId).action = actionName;

        document.getElementById(formId).submit();
    } else {
        alert('No row selected.')
    }
}

function deleteByCheckBoxName(actionName, formId, listCheckboxName) {
    myForm = document.getElementById(formId);
    showConfirmDelete = false;
    for (i = 0; i < myForm.elements.length; i++) {
        var element = myForm.elements[i];
        if (element.checked) {
            if (element.id == listCheckboxName) {
                showConfirmDelete = true;
                break;
            }
        }
    }
    if (showConfirmDelete == true) {
        if (ConfirmDelete()) {
            document.getElementById(formId).dispatch.value = actionName;
            document.getElementById(formId).submit();
        }
    } else {
        alert('No row selected for deleting.')
    }
}


function conversionPopup(pdfConverterURL, pid, iid, cuid, uid) {
    url = pdfConverterURL + '?pid=' + pid + '&iid=' + iid + '&cuid=' + cuid + '&uid=' + uid;
    popwinConv = window.open(url, 'popwinConv', 'width=500,height=300,left=0,top=0,toolbar=No,location=No,scrollbars=Yes,status=No,resizable=Yes,fullscreen=No');
    if (popwinConv) {
        popwinConv.focus();
    }
    return false;
}

function openClippingTool(clipToolURL, pid, iid, cuid, uid) {
    url = clipToolURL + '?pid=' + pid + '&iid=' + iid + '&cuid=' + cuid + '&uid=' + uid;
    popwinCliptool = window.open(url, 'popwinCliptool', 'width=1240,height=990,left=0,top=0,toolbar=No,location=No,scrollbars=Yes,status=No,resizable=Yes,fullscreen=No');
    if (popwinCliptool) {
        popwinCliptool.focus();
    }
    return false;
}

function previewIssue(previewURL, pid, iid, cuid, uid) {
    url = previewURL + '?pid=' + pid + '&iid=' + iid + '&cuid=' + cuid + '&uid=' + uid;
    popwinIssuepreview = window.open(url, 'popwinIssuepreview', 'width=1020,height=690,left=0,top=0,toolbar=No,location=No,scrollbars=Yes,status=No,resizable=Yes,fullscreen=No');
    if (popwinIssuepreview) {
        popwinIssuepreview.focus();
    }
    return false;
}

function previewIssuePage(previewURL, pid, iid, cuid, uid, pageNo) {
    url = previewURL + '?pid=' + pid + '&iid=' + iid + '&cuid=' + cuid + '&uid=' + uid + '&spread=' + pageNo;
    popwinIssuepreview = window.open(url, 'popwinIssuepreview', 'width=1020,height=690,left=0,top=0,toolbar=No,location=No,scrollbars=Yes,status=No,resizable=Yes,fullscreen=No');
    if (popwinIssuepreview) {
        popwinIssuepreview.focus();
    }
    return false;
}

function tipBody(column, pageNo, lImageUrl, rImageUrl, previewBaseUrl, pubId, isId, cId, uId) {
    var imagePath ;
    if (column == 'Spread') {
        if (lImageUrl != '' && rImageUrl != '') {
            imagePath = '<img  src=' + lImageUrl + '>' + '<img  src=' + rImageUrl + '>';
        }
        else if (lImageUrl != '') {
            imagePath = '<img  src=' + lImageUrl + '>';
        } else {
            imagePath = '<img  src=' + rImageUrl + '>';
        }
    }
    if (column == 'Left') {
        imagePath = '<img  src=' + lImageUrl + '>';
    }
    if (column == 'Right') {
        imagePath = '<img  src=' + rImageUrl + '>';
        pageNo = pageNo + 1;
    }
    var result = "<a href='#' onclick=\"javascript:return previewIssuePage(\x27" + previewBaseUrl + "\x27,\x27" + pubId + "\x27,\x27" + isId + "\x27,\x27" + cId + "\x27,\x27" + uId + "\x27,\x27" + pageNo + "\x27)\"> " + imagePath + "<\/a>";
    return result;
}

function ConfirmUpdate() {
    return confirm('Do you realy want to replace current version with updated version?');
}

function ConfirmRestore() {
    return confirm('Do you realy want to Restore the backup zip file?');
}

function printPage() {
    window.print();
}

function pagerGotoMaxPage(inputField, e, url, paramName) {
    var value = inputField.value;
    var computedUrl = url;

    if (isNaN(parseInt(value, 10))) {
        return;
    }
    if (value <= 0) {
        return;
    }

    if (url.indexOf("?") == -1) {
        computedUrl += "?";
    } else {
        computedUrl += "&";
    }
    computedUrl += paramName + "=" + value;
    document.location = computedUrl;

}

function openHelpWindow(helpId)
{
    URL = '/help/help.jsp?id=' + helpId;
    windowName = 'helpWindow';
    windowOptions = 'width=700,height=500,resizable=yes';
    var newWindow = open(URL, windowName, windowOptions)
    newWindow.focus();
}

function ConfirmResend() {
    return confirm('Do you realy want to resend E-Mails?');
}

function resendByCheckBox(actionName, formId) {
    myForm = document.getElementById(formId);
    showConfirmResend = false;
    for (i = 0; i < myForm.elements.length; i++) {
        var element = myForm.elements[i];
        if (element.checked) {
            showConfirmResend = true;
            break;
        }
    }

    if (showConfirmResend == true) {
        if (ConfirmResend()) {
            document.getElementById(formId).dispatch.value = actionName;
            document.getElementById(formId).submit();
        }
    } else {
        alert('No row selected for Resending Action.')
    }
}

function checkInput(val, validCharacters) {
    for (var i = 0; i < val.length; i++) {
        if (validCharacters.indexOf(val.charAt(i)) < 0) {
            return false;
        }
    }
    return true;
}

function addItemToList(source, destination, mode) {
    //mode = 1 ==> only "1234567890"
    //mode = 2 ==> only "1234567890 +"
    var inputValue = document.getElementsByName(source)[0].value;
    if (inputValue == '') {
        return;
    }

    accept = true;
    if (mode == 1) {
        accept = checkInput(inputValue, "1234567890");
    } else if (mode == 2) {
        accept = checkInput(inputValue, "1234567890 +");
    }

    if (accept == false) {
        alert('invalid characters');
        return;
    }

    document.getElementsByName(destination)[0].options.add(new Option(inputValue, inputValue));
    document.getElementsByName(source)[0].value = '';
}

function addItemFromListToList(source, destination, indexId) {
    if (indexId == null) {
        indexId = 0;
    }

    var dropdown = document.getElementsByName(source)[indexId];
    if (dropdown.selectedIndex == '0') {
        return;
    }
    var inputValue = dropdown.options[dropdown.selectedIndex].text;
    inputValue = '#' + inputValue + '#';
    document.getElementsByName(destination)[0].options.add(new Option(inputValue, inputValue));
}

function addItemFromListToTextarea(source, destination, indexId) {
    if (indexId == null) {
        indexId = 0;
    }

    var dropdown = document.getElementsByName(source)[indexId];
    if (dropdown.selectedIndex == '0') {
        return;
    }
    var inputValue = dropdown.options[dropdown.selectedIndex].text;
    inputValue = '#' + inputValue + '#';
    document.getElementsByName(destination)[0].value = document.getElementsByName(destination)[0].value + inputValue;
}

function removeItemFromList(selectboxName) {
    var selectbox = document.getElementsByName(selectboxName)[0];
    for (var i = selectbox.options.length - 1; i >= 0; i--) {
        if (selectbox.options[i].selected) {
            selectbox.remove(i);
        }
    }
}

function selectAllItems(selectboxName) {
    var selectbox = document.getElementsByName(selectboxName)[0];
    for (var i = selectbox.options.length - 1; i >= 0; i--) {
        selectbox.options[i].selected = true;
    }
}