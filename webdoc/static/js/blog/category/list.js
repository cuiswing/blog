/**
 * Created by cuishixiang on 2017-09-15.
 */

$(document).ready(function () {
    var category_list = new Vue({
        el: "#category",
        data: {
            infos: {},
            category: {
                "id": 0,
                "categoryName": ""
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.loadGridData();
            })
        },
        methods: {
            loadGridData: function () {
                $.ajax({
                    type: "GET",
                    url: __ctx + "/category/list",
                    success: function (result) {
                        if (result.success) {
                            Vue.set(category_list.infos, 'data', result.data);
                        } else {
                            toastr.error(result.errorMessage, "加载失败", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                        }
                    },
                    error: function (result) {
                        toastr.error(result.message, "加载失败", {
                            timeOut: 2000,
                            positionClass: "toast-top-center"
                        });
                    }
                })
            },
            edit: function (id, categoryName) {
                this.category.id = id;
                this.category.categoryName = categoryName;
                $("#formModal").modal({
                    show: true,
                    keyboard: true,
                    backdrop: 'static'
                });
            },
            save: function () {
                $.ajax({
                    type: "POST",
                    url: __ctx + "/admin/category/save",
                    data: JSON.stringify(category_list.category),
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    success: function (result) {
                        if (result.success) {
                            toastr.success("", "保存成功", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                            category_list.loadGridData()
                            $("#formModal").modal('hide')
                        } else {
                            toastr.error(result.errorMessage, "保存失败", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                        }
                    },
                    error: function (result) {
                        toastr.error(result, "保存失败", {
                            timeOut: 2000,
                            positionClass: "toast-top-center"
                        });
                    }
                });
            }
        }

    })
})