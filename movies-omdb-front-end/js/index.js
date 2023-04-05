$(document).ready(function() {
  let loggedInUser; // Define loggedInUser variable

  $("#loginForm").submit(function(event) {
      event.preventDefault(); // Prevent the form from submitting normally

      // Get form data
      const formData = {
          username: $("#username").val(),
          password: $("#password").val()
      };

      // Send the form data using AJAX
      $.ajax({
          type: "POST",
          url: "http://localhost:8080/api/login/authenticate",
          data: formData,
          dataType: "json",
          encode: true
      })
      .done(function(data) {
          // Store the logged-in user's username
          loggedInUser = formData.username;

          // Display a greeting message
          alert("Hello " + loggedInUser);
      })
      .fail(function() {
          alert("Invalid username or password");
      });
  });

  // Search for a movie script

  const $button = $('.btn-search');
  const $input = $('.search-input');
  const $movieDetails = $('#movie-details');

  $button.on('click', () => {
    const title = $input.val();

    $.ajax({
      type: 'GET',
      url: `http://localhost:8080/api/movies/title?title=${title}&includePoster=true`,
      dataType: 'json'
    })
    .done(data => {
      $movieDetails.html(`
        <h2>${data.Title}</h2>
        <img src="${data.Poster}" alt="${data.Title} poster">
        <p>${data.Plot}</p>
        <p>Directed by: ${data.Director}</p>
        <p>Starring: ${data.Actors}</p>
      `);

      const $watchlistButton = $('.watchlist-button');

      $watchlistButton.on('click', () => {
        if (!loggedInUser) {
          console.error('User not logged in');
          return;
        }

        const requestData = {
          username: loggedInUser,
          director: data.Director,
          title: data.Title
        };

        $.ajax({
          type: 'POST',
          url: `http://localhost:8080/api/users/movies/watchlist/${requestData.username}/${requestData.title}/${requestData.director}`,
          data: JSON.stringify(requestData),
          contentType: 'application/json',
          xhrFields: {
            withCredentials: true
          }
        })
        .done(() => {
          // location.reload();
        })
        .fail((xhr, statusText, error) => {
          console.error(`Failed to add movie to watchlist: ${statusText}`);
          console.error(xhr);
          console.error(error);
          console.log(requestData);
        });
      });
    })
    .fail((xhr, statusText, error) => {
      console.error('Error getting movie data:', error);
      console.log(xhr);
      console.log(statusText);
    });
  });
});