// wait for the DOM to be loaded
$(document).ready(function() {
    // bind 'myForm' and provide a simple callback function
    $('#login').ajaxForm(function(x) {
        alert("Logged in")
        sessionStorage.setItem("sessionToken", x.JWT);
    });
});

function logOut() {
    alert("Logged out");
    sessionStorage.clear();
}