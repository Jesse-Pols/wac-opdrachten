// wait for the DOM to be loaded
$(document).ready(function() {
    // bind 'myForm' and provide a simple callback function
    $('#login').ajaxForm(function(x) {
        console.log("Logged in");
        sessionStorage.setItem("sessionToken", x.JWT);
    });
});