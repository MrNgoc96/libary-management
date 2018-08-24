<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<script src="resources/js/dialog-utils.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<script>
    $(document).ready(function () {
        $('#selected-page').change(function () {
            let page = $(this).val();
            changeQueryString('page',page);
        });

        let isVn = $('#isVn').val();

        if(isVn == 'true'){
            $('.date-picker').datepicker({
                format: 'dd/mm/yyyy'
            });
        }else{
            $('.date-picker').datepicker({
                format: 'mm/dd/yyyy'
            });
        }
        let message = $('.actionMessage').text();
        if (message != '') {
            let title = (isVn == 'true') ? 'Thông báo' : 'Inform';
            showPopup(title, message);
        }
        if($('body').has('#image')){
            $('.image').change(function () {
                let input = this;
                let url = $(this).val();
                let ext = url.substring(url.lastIndexOf('.') + 1)
                    .toLowerCase();
                if (input.files
                    && (ext == "gif" || ext == "png"
                        || ext == "jpeg" || ext == "jpg")) {
                    let reader = new FileReader();
                    reader.onload = function (e) {
                        let image = '<img src="'+e.target.result+'" align="center" style="margin-left:35%;max-width: 150px;max-height: 150px"/>';
                        $('.img').html(image);
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            })
        }
    })
</script>
<script>
    function remove(actionRemove, removeName) {
        let isVn = $('#isVn').val();
        let title = (isVn == 'true') ? 'Thông báo' : 'Inform';
        let message = (isVn == 'true') ? 'Bạn có chắc muốn xóa <b>' + removeName + '</b> ?' : 'Do you want to remove <b>' + removeName + '</b> ?';
        let ok = (isVn == 'true') ? 'Đồng ý' : 'Ok';
        let cancel = (isVn == 'true') ? 'Hủy' : 'Cancel'
        Confirm(title, message, ok, cancel, actionRemove);
    }
    function changeQueryString(key, value) {
        let queryString = $('#query-string').val();
        if (queryString == '') {
            window.location.href = '?' + key + '=' + value;
        } else {
            let params = queryString.split('&');
            let isInQuery = false;
            let oldParam = '';
            for (let p of params) {
                let paramKey = p.split("=")[0];
                if (paramKey == key) {
                    oldParam = p;
                    isInQuery = true;
                    break;
                }
            }
            if (isInQuery) {
                queryString = queryString.replace(oldParam, key + '=' + value);
            } else {
                queryString += '&' + key + '=' + value;
            }
            window.location.href = '?' + queryString;
        }
    }
</script>
</html>