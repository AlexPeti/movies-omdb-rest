  // // Retrieve the value of the username cookie
  // function getCookieValue(cookieName) {
  //   console.log(`document.cookie: ${document.cookie}`);
  //   const cookies = document.cookie.split(';');
  //   for (let i = 0; i < cookies.length; i++) {
  //     const cookie = cookies[i].trim();
  //     if (cookie.startsWith(`${cookieName}=`)) {
  //       console.log(`Found cookie: ${cookie}`);
  //       return cookie.substring(`${cookieName}=`.length);
  //     }
  //   }
  //   console.log(`Cookie ${cookieName} not found`);
  //   return null; // Return null if the cookie is not found
  // }
  
  // // Get the value of the username cookie
  // const username = getCookieValue('username');
  
  // if (username) {
  //   // Make an HTTP GET request to the Java endpoint
  //   $.ajax({
  //     url: 'http://localhost:8080/api/user/getMoviesByUsername', // Replace with your endpoint URL
  //     type: 'GET',
  //     dataType: 'json',
  //     beforeSend: function(xhr) {
  //       xhr.setRequestHeader('Authorization', 'Bearer ' + username);
  //     },
  //     success: function(response) {
  //       // Handle successful response
  //       console.log(response); // Example usage
  //     },
  //     error: function(jqXHR, textStatus, errorThrown) {
  //       // Handle error
  //       console.error(textStatus, errorThrown);
  //     }
  //   });
  // } else {
  //   // Handle case where username cookie is not found
  //   console.error('Username cookie not found');
  // }
  
  