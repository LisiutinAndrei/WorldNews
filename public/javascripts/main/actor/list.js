$(function () {
    $("#paging>a").click(function (e) {
        e.preventDefault();
        var m = this.href.match(/page=(\d+)/i);
        if (m) {
            $("#page").attr("value", m[1]);
            $("#actorsSearchForm").submit();
        }
        return false;
    });
});