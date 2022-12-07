<script type="text/javascript" src="<%= getServletContext().getContextPath()%>/Mootools/mootools-yui-compressed.js"></script>
<script type="text/javascript" src="<%= getServletContext().getContextPath()%>/Mootools/seylightbox.v2.3.mootools.js"></script>
<link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/Mootools/seylightbox.css" type="text/css" media="all" />
<script type="text/javascript">
      function GetWidth()
      {
              var x = 0;
              if (self.innerHeight)
              {
                      x = self.innerWidth;
              }
              else if (document.documentElement && document.documentElement.clientHeight)
              {
                      x = document.documentElement.clientWidth;
              }
              else if (document.body)
              {
                      x = document.body.clientWidth;
              }
              return x;
      }
      function GetHeight()
      {
              var y = 0;
              if (self.innerHeight)
              {
                      y = self.innerHeight;
              }
              else if (document.documentElement && document.documentElement.clientHeight)
              {
                      y = document.documentElement.clientHeight;
              }
              else if (document.body)
              {
                      y = document.body.clientHeight;
              }
              return y;
      }
  window.addEvent('domready', function(){
//  SeyLightbox = new SeyLightBox({
//    find          : 'seylightbox', // rel="seylightbox"
//    color         : 'black',
//    dir           : '<%= getServletContext().getContextPath()%>/Mootools/seyimages/',
//    emergefrom    : 'top',
//    OverlayStyles : {
//      'background-color': '#000000',
//      'opacity' : 0.5
//    }
//  });
  SeyLightbox = new SeyLightBox({
    find          : 'seylightbox', // rel="seylightbox"
    color         : 'black',
    dir           : '<%= getServletContext().getContextPath()%>/Mootools/seyimages/',
    emergefrom    : 'top',
    OverlayStyles : {
      'background-color': '#000000',
      'opacity' : 0.5
    }
  });
});
//  alert(GetHeight());  
</script>
<noscript>Your browser does not support JavaScript!</noscript>