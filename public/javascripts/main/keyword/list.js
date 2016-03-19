$(function () {
    $("#paging>a").click(function (e) {
        e.preventDefault();
        var m = this.href.match(/page=(\d+)/i);
        if (m) {
            $("#page").attr("value", m[1]);
            $("#keywordSearchForm").submit();
        }
        return false;
    });
});