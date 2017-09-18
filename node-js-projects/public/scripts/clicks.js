$(document).ready(function() {
    $(".add").on("click", function() {
        alert('Clicked the button');
        window.location.href = '/schedule/course/create';
    });

    $(".update").on("click", function() {
        alert('Clicked the button');
        window.location.href = '/schedule/course/search/update';
    });

    $(".delete").on("click", function() {
        alert('Clicked the button');
        window.location.href = '/schedule/course/search/delete';
    });

    // $(".confirm-delete").on("click", function() {
    //     alert('Clicked the button');
    //     window.location.href = '/schedule/course/delete';
    // });

    $("li").on("mouseenter", function() {
        $(this).find(".hand").css("transform", "translateX(40px)");
    });

    $("li").on("mouseleave", function() {
        $(this).find(".hand").css("transform", "translateX(0px)");
    });

    // alert('works');
});
