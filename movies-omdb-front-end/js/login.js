document.querySelector("form").addEventListener("submit", function(event) {
    event.preventDefault();

    const username = document.querySelector("input[name='username']").value;
    const password = document.querySelector("input[name='password']").value;

    fetch("http://localhost:8080/api/login/authenticate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            // "Accept" : "application/json"
        },
        body: JSON.stringify({ username: username, password: password })
    })
    .then(response => {
        if (response.ok) {
            window.location.replace("/index.html");
        } else {
            alert("Invalid username or password");
        }
    })
    .catch(error => console.error(error));
});

