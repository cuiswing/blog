$(document).ready(function () {

    var vue = new Vue({
        el: "#loginForm",
        data: {
            params: {}
        },
        ready: {},
        methods: {
            login: function () {
                $.ajax({
                    type: "POST",
                    url: __ctx + "/admin/account/login",
                    data: vue.params,
                    success: function (result) {
                        if (result.success) {
                            location.href = __ctx + "/admin/article";
                        } else {
                            toastr.error(result.errorMessage, "登陆失败", {
                                timeOut: 3000,
                                positionClass: "toast-top-center"
                            })
                        }
                    },
                    error: function (result) {
                        toastr.error("", "登陆失败", {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        })
                    }
                })
            }

        }
    });
})