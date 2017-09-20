/**
 * Created by cuishixiang on 2017-09-18.
 */

$(document).ready(function () {
    var article_edit = new Vue({
        el: "#article",
        data: {
            articleId: articleId,
            articleVO: {},
            categoryList: {}
        },
        mounted: function () {
            this.$nextTick(function () {
                this.loadAllCategory();
                this.loadData();
            })
        },
        methods: {
            loadData: function () {
                if (articleId > 0) {
                    $.ajax({
                        type: "GET",
                        url: __ctx + "/admin/article/" + articleId,
                        success: function (result) {
                            if (result.success) {
                                article_edit.articleVO = result.data;
                                window.setTimeout(function () {
                                    $("#categorySelect").val(article_edit.articleVO.articleCategoryId).trigger('change')
                                }, 900);
                                weditor.txt.html(article_edit.articleVO.content);
                            } else {
                                toastr.error(result, "加载文章失败", {
                                    timeOut: 2000,
                                    positionClass: "toast-top-center"
                                })
                            }
                        },
                        error: function (result) {
                            toastr.error(result, "加载文章失败", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                        }
                    });
                } else {
                    Vue.set(article_edit.articleVO, "id", 0);
                }
            },
            loadAllCategory: function () {
                $.ajax({
                    type: "GET",
                    url: __ctx + "/admin/category/list",
                    success: function (result) {
                        if (result.success) {
                            article_edit.categoryList = result.data
                        } else {
                            toastr.error(result.errorMseeage, "加载文章类别失败", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                        }
                    },
                    error: function (result) {
                        toastr.error(result, "加载文章类别失败", {
                            timeOut: 2000,
                            positionClass: "toast-top-center"
                        })
                    }
                });
            },
            save: function () {
                article_edit.articleVO.content = weditor.txt.html();
                $.ajax({
                    type: "POST",
                    url: __ctx + "/admin/article/save",
                    data: JSON.stringify(article_edit.articleVO),
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    success: function (result) {
                        if (result.success) {
                            toastr.success("", "保存成功", {
                                timeOut: 2000,
                                positionClass: "toast-top-center"
                            })
                            window.setTimeout(function () {
                                location.href = __ctx + "/admin/article/edit?id=" + result.data.id
                            }, 1000);
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
                console.log(article_edit.articleVO)
            }
        }
    });

    $("#categorySelect").select2({
        placeholder: "选择分类",
        language: "zh-CN",
        allowClear: true
    }).on("change", function (e) {
        if (!e.added) {
            Vue.set(article_edit.articleVO, "articleCategoryId", "");
            Vue.set(article_edit.articleVO, "categoryName", "");
        } else if (e.added.id) {
            Vue.set(article_edit.articleVO, "articleCategoryId", e.added.id);
            Vue.set(article_edit.articleVO, "categoryName", e.added.text.trim());
        }
    });

    var WEditor = window.wangEditor;
    var weditor = new WEditor('#weditor');
    weditor.customConfig.zIndex = 100;
    weditor.create();


});