$(document).ready (function() {
    $("#burger-nav").on("click", function(){
        $(this).toggleClass("open");
    });

    var $window = $(window);
    var $element = $("#img-panel img");

    $window.on("scroll", function(){
        var window_height = $window.height();
        var window_top_position = $window.scrollTop();
        var window_bottom_position = (window_top_position + window_height);

        var element_height = $element.outerHeight();
        var element_top_position = $element.offset().top;
        var element_bottom_position = (element_top_position + element_height);
   
        //check to see if this current container is within viewport
        if ((element_bottom_position >= window_top_position) &&
            (element_top_position <= window_bottom_position)) {
            // $element.addClass('in-view');
            // $("#side-panel").css("position", "relative");
            $("#side-panel").fadeOut(80);
        } else {
            // $element.removeClass('in-view');
            $("#side-panel").css("position", "fixed");
            $("#side-panel").fadeIn(800);
        }
    });
});