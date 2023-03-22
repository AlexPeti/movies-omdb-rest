const button = document.querySelector('.btn-search');
const input = document.querySelector('.search-input');
const movieDetails = document.getElementById('movie-details');

button.addEventListener('click', () => {
    const title = input.value;
    fetch(`http://localhost:8080/api/movies/title?title=${title}`)
      .then(response => response.json())
      .then(data => {
        // Update the HTML with the movie details
        movieDetails.innerHTML = `
          <h2>${data.Title}</h2>
          <p>${data.Plot}</p>
          <p>Directed by: ${data.Director}</p>
          <p>Starring: ${data.Actors}</p>
        `;
      })
      .catch(error => {
        // Handle any errors that occur
        console.error(error);
      });
  });