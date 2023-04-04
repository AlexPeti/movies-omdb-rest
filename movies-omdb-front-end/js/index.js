//SCRIPT BELOW WORKS, epitelous KANEI ALERT SWSTA!!!!

// Log-in script

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

// // Search for a movie script

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


/////// CODE ABOVE WORKS, EXCEPT ADD MOVIE TO WATCHLIST ////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////





// $(document).ready(function() {
//   let loggedInUser; // Define loggedInUser variable

//   $("#loginForm").submit(function(event) {
//       event.preventDefault(); // Prevent the form from submitting normally

//       // Get form data
//       const formData = {
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
//           // Store the logged-in user's username
//           loggedInUser = formData.username;

//           // Display a greeting message
//           alert("Hello " + loggedInUser);
//       })
//       .fail(function() {
//           alert("Invalid username or password");
//       });
//   });

//   // Search for a movie script

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
//         <img src="${data.Poster}" alt="${data.Title} poster">
//         <p>${data.Plot}</p>
//         <p>Directed by: ${data.Director}</p>
//         <p>Starring: ${data.Actors}</p>
//       `);

//       const $watchlistButton = $('.watchlist-button');

//       $watchlistButton.on('click', () => {
//         if (!loggedInUser) {
//           console.error('User not logged in');
//           return;
//         }

//         const requestData = { title: data.Title, director: data.Director, username: loggedInUser }; // Include username, title, and genre in the request body

//         $.ajax({
//           type: 'POST',
//           url: `http://localhost:8080/api/users/username/${loggedInUser}/movies/watchlist`,
//           // data: JSON.stringify(requestData),
//           data: requestData,
//           dataType: "json",
//           endoced: true,
//           contentType: 'application/json',
//           xhrFields: {
//             withCredentials: true
//           },
//           error: function(jqXHR, textStatus, errorThrown) {
//             console.log(jqXHR.responseText);
//             console.log(textStatus);
//             console.log(errorThrown);
//             console.log(requestData)
//           }
//         })
//         .done(() => {
//           // location.reload();
//         })
//         .fail((xhr, statusText, error) => {
//           console.error(`Failed to add movie to watchlist: ${statusText}`);
//           console.error(xhr);
//           console.error(error);
//         });
//       });
//     })
//     .fail((xhr, statusText, error) => {
//       console.error('Error getting movie data:', error);
//       console.log(xhr);
//       console.log(statusText);
//     });
//   });
// });

////////////////////////////////////


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






        





















