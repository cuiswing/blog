/**
 * Created by cuishixiang on 2017-09-18.
 */

$(document).ready(function () {
    var article_edit = new Vue({
        el: "#article",
        data: {
            articleId: articleId,
            articleVO: {},
            categoryList: categoryList
        },
        mounted: function () {
            this.$nextTick(function () {
                // this.loadAllCategory();
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
                                // window.setTimeout(function () {
                                $("#categorySelect").val(article_edit.articleVO.articleCategoryId).trigger('change')
                                // }, 900);
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
    weditor.customConfig.uploadImgServer = __ctx + '/admin/image/upload';  // 上传图片到服务器
    weditor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;  // 将图片大小限制为 5M
    // 限制一次最多上传 5 张图片
    weditor.customConfig.uploadImgMaxLength = 5;
    weditor.customConfig.uploadImgTimeout = 30000;
    weditor.customConfig.uploadFileName = 'image';
    weditor.customConfig.uploadImgHooks = {
        // before: function (xhr, editor, files) {
        //     // 图片上传之前触发
        //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
        //
        //     // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
        //     // return {
        //     //     prevent: true,
        //     //     msg: '放弃上传'
        //     // }
        // },
        // success: function (xhr, editor, result) {
        //     // 图片上传并返回结果，图片插入成功之后触发
        //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        // },
        // fail: function (xhr, editor, result) {
        //     // 图片上传并返回结果，但图片插入错误时触发
        //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        // },
        // error: function (xhr, editor) {
        //     // 图片上传出错时触发
        //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        // },
        // timeout: function (xhr, editor) {
        //     // 图片上传超时时触发
        //     // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        // },

        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // result 必须是一个 JSON 格式字符串！！！否则报错

            if (result.success && result.data) {
                var imageUrls = result.data;
                for (var i = 0; i < imageUrls.length; i++) {
                    insertImg(imageUrls[i]);
                }
            } else {
                toastr.error(result, "上传图片失败", {
                    timeOut: 2000,
                    positionClass: "toast-top-center"
                });
            }
        }
    };
    weditor.create();


});