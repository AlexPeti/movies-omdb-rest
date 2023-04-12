// Log-in script

  $(document).ready(function() {
  let loggedInUser; 
  $("#loginForm").submit(function(event) {
      event.preventDefault();

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
          encode: true,
          xhrFields: {
            withCredentials: true
        }
      })
      .done(function(data) {
          // Store the logged-in user's username
          loggedInUser = formData.username;

          // Display a greeting message
          alert("Welcome " + loggedInUser);
      })
      .fail(function() {
          alert("Invalid username or password");
      });
  });

  // Searching for movies script

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

// Adding a movie to the logged in user's watchlist logic

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


// AJAX call to retrieve the logged in user's watchlist
  $('#myMovies').on('click', function() {
    if (!loggedInUser) {
      console.error('User not logged in');
      return;
    }
    // Call the /user/watchlist endpoint to get movies by username
    $.ajax({
      url: `http://localhost:8080/api/user/watchlist?username=${loggedInUser}`,
      type: 'GET',
      dataType: 'json',
      xhrFields: {
        withCredentials: true
      }
    })
    .done(function(data) {
      // Clear existing movie list
      $('#myMovieContainer').empty();
      // Loop through the movies and append only the movie titles to the container
      for (let i = 0; i < data.movies.length; i++) {
        const movieTitle = data.movies[i].title;
        // Append movie title to a div container
        $('#myMovieContainer').append(`<div>${movieTitle}</div>`);
      }
    })
    .fail(function(xhr, statusText, error) {
      console.error('Error getting watchlist:', error);
      console.log(xhr);
      console.log(statusText);
    });
  });
});