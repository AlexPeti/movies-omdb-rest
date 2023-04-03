  // // Get the search button and input field
  // const button = document.querySelector('.btn-search');
  // const input = document.querySelector('.search-input');
  // const movieDetails = document.getElementById('movie-details');
  
  // // Add a click event listener to the search button
  // button.addEventListener('click', () => {
  //   const title = input.value;
  
  //   // Added the &includePoster=true so it also displays the movie poster
  //   fetch(`http://localhost:8080/api/movies/title?title=${title}&includePoster=true`)
  //     .then(response => response.json())
  //     .then(data => {
  //       // Update the HTML with the movie details
  //       movieDetails.innerHTML = `
  //         <h2>${data.Title}</h2>
  //         <button class="watchlist-button" data-movie-title="${data.Title}">+ watchlist</button>
  //         <img src="${data.Poster}" alt="${data.Title} poster">
  //         <p>${data.Plot}</p>
  //         <p>Directed by: ${data.Director}</p>
  //         <p>Starring: ${data.Actors}</p>
  //       `;
  
  //       // Get the watchlist button and its associated movie title
  //       const watchlistButton = document.querySelector('.watchlist-button');
  //       const movieTitle = watchlistButton.dataset.movieTitle;
  
  //       // Add a click event listener to the watchlist button
  //       watchlistButton.addEventListener('click', () => {
  //         if (!loggedInUser) {
  //           console.error('User not logged in');
  //           return;
  //         }
  
  //         // Make a POST request to add the movie to the user's watchlist
  //         fetch(`http://localhost:8080/api/users/username/${loggedInUser}/movies/watchlist`, {
  //           method: 'POST',
  //           headers: {
  //             'Content-Type': 'application/json'
  //           },
  //           body: JSON.stringify({
  //             title: movieTitle
  //           }),
  //           credentials: 'include'
  //         })
  //         .then(response => {
  //           if (response.ok) {
  //             // Reload the page to show the updated watchlist
  //             location.reload();
  //           } else {
  //             console.error(`Failed to add movie to watchlist: ${response.statusText}`);
  //           }
  //         })
  //         .catch(error => {
  //           console.error('Error adding movie to watchlist:', error);
  //         });
  //       });
  //     })
  //     .catch(error => {
  //       console.error('Error getting movie data:', error);
  //     });
  // });



// SCRIPTS ABOVE WORK FOR LOGGING IN BUT IT REDIRECTS TO TOMCAT

//SCRIPT BELOW WORKS, epitelous KANEI ALERT SWSTA!!!!

// $(document).ready(function() {
//   $("#loginForm").submit(function(event) {
//       event.preventDefault(); // Prevent the form from submitting normally

//       // Get form data
//       var formData = {
//           username: $("#username").val(),
//           password: $("#password").val()
//       };

//       // Send the form data using AJAX
//       $.ajax({
//           type: "POST",
//           url: "http://localhost:8080/api/login/authenticate",
//           data: formData,
//           dataType: "json",
//           encode: true
//       })
//       .done(function(data) {
//           // Display a greeting message
//           alert("Hello " + formData.username);
//       })
//       .fail(function() {
//           alert("Invalid username or password");
//       });
//   });
// });

// /////////////////////////////////////////////////////

// $(document).ready(function() {
//   const $button = $('.btn-search');
//   const $input = $('.search-input');
//   const $movieDetails = $('#movie-details');

//   $button.on('click', () => {
//     const title = $input.val();

//     $.ajax({
//       type: 'GET',
//       url: `http://localhost:8080/api/movies/title?title=${title}&includePoster=true`,
//       dataType: 'json'
//     })
//     .done(data => {
//       $movieDetails.html(`
//         <h2>${data.Title}</h2>
//         <button class="watchlist-button" data-movie-title="${data.Title}">+ watchlist</button>
//         <img src="${data.Poster}" alt="${data.Title} poster">
//         <p>${data.Plot}</p>
//         <p>Directed by: ${data.Director}</p>
//         <p>Starring: ${data.Actors}</p>
//       `);

//       const $watchlistButton = $('.watchlist-button');
//       const movieTitle = $watchlistButton.data('movie-title');

//       $watchlistButton.on('click', () => {
//         if (!loggedInUser) {
//           console.error('User not logged in');
//           return;
//         }

//         $.ajax({
//           type: 'POST',
//           url: `http://localhost:8080/api/users/username/${loggedInUser}/movies/watchlist`,
//           data: JSON.stringify({ title: movieTitle }),
//           contentType: 'application/json',
//           xhrFields: {
//             withCredentials: true
//           }
//         })
//         .done(() => {
//           location.reload();
//         })
//         .fail((xhr, statusText, error) => {
//           console.error(`Failed to add movie to watchlist: ${statusText}`);
//         });
//       });
//     })
//     .fail((xhr, statusText, error) => {
//       console.error('Error getting movie data:', error);
//     });
//   });
// });

$(document).ready(function() {
  // Store the username in a variable after the user logs in
  var loggedInUser;

  $("#loginForm").submit(function(event) {
      event.preventDefault(); // Prevent the form from submitting normally

      // Get form data
      var formData = {
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
          // Store the username in the variable
          loggedInUser = formData.username;

          // Display a greeting message
          alert("Hello " + loggedInUser);
      })
      .fail(function() {
          alert("Invalid username or password");
      });
  });

  // Get the search button and input field
  const button = document.querySelector('.btn-search');
  const input = document.querySelector('.search-input');
  const movieDetails = document.getElementById('movie-details');

  // Add a click event listener to the search button
  button.addEventListener('click', () => {
    const title = input.value;

    // Added the &includePoster=true so it also displays the movie poster
    fetch(`http://localhost:8080/api/movies/title?title=${title}&includePoster=true`)
      .then(response => response.json())
      .then(data => {
        // Update the HTML with the movie details
        movieDetails.innerHTML = `
          <h2>${data.Title}</h2>
          <button class="watchlist-button" data-movie-title="${data.Title}">+ watchlist</button>
          <img src="${data.Poster}" alt="${data.Title} poster">
          <p>${data.Plot}</p>
          <p>Directed by: ${data.Director}</p>
          <p>Starring: ${data.Actors}</p>
        `;

        // Get the watchlist button and its associated movie title
        const watchlistButton = document.querySelector('.watchlist-button');
        const movieTitle = watchlistButton.dataset.movieTitle;

        // Add a click event listener to the watchlist button
        watchlistButton.addEventListener('click', () => {
          if (!loggedInUser) {
            console.error('User not logged in');
            return;
          }

          // Make a POST request to add the movie to the user's watchlist
          $.ajax({
            type: "POST",
            url: `http://localhost:8080/api/users/${loggedInUser}/movies/watchlist`,
            data: JSON.stringify({
              title: movieTitle
            }),
            contentType: 'application/json',
            xhrFields: {
              withCredentials: true
            },
            crossDomain: true,
            success: function(data) {
              // Reload the page to show the updated watchlist
              location.reload();
            },
            error: function(xhr, status, error) {
              console.error(`Failed to add movie to watchlist: ${error}`);
            }
          });
        });
      })
      .catch(error => {
        console.error('Error getting movie data:', error);
      });
  });
});




















