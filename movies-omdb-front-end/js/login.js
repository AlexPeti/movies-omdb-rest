document.querySelector("form").addEventListener("submit", function(event) {
    event.preventDefault();

    const username = document.querySelector("input[name='username']").value.trim();
    const password = document.querySelector("input[name='password']").value.trim();

    fetch("http://localhost:8080/api/login/authenticate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: new URLSearchParams({
            username: username,
            password: password
        })
    })
    .then(response => {
        if (response.ok) {
            window.location.replace = "http://127.0.0.1:5500/index.html";
        } else {
            alert("Invalid username or password");
        }
    })
    .catch(error => console.error(error));
});