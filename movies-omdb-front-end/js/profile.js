$('#myMovies').on('click', function() {
  const loggedInUser = localStorage.getItem('loggedInUser')
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
})
  
  