/**
 * Created by cuishixiang on 2017-09-14.
 */

$(document).ready(function () {
    var article_list = new Vue({
        el: "#articleList",
        data: {
            params: {},
            infos: {}
        },
        mounted: function () {
            this.$nextTick(function () {
                this.loadGridData(this.params);
            })
        },
        methods: {
            loadGridData: function (param) {
                $.ajax({
                    type: "POST",
                    url: __ctx + "/admin/article/list",
                    data: param,
                    success: function (result) {
                        if (result.success) {
                            article_list.infos = result.data
                        } else {
                            toastr.error(result.errorMessage, "加载文章失败", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                        }
                    },
                    error: function (result) {
                        toastr.error(result, "加载文章失败", {
                            timeOut: 2000,
                            positionClass: "toast-top-center"
                        });
                    }
                })
            },
            queryData: function (event, pageInfo) {
                if (pageInfo) {
                    $.extend(this.params, pageInfo);
                } else {
                    this.params.pageNo = 1;
                    this.params.pageSize = 20;
                }
                this.loadGridData(this.params);
            },
        }

    })
})