/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container } from "reactstrap";

import { useGlobalState } from "../../GlobalState";
// core components

function IndexHeader({name, region}) {
  let pageHeader = React.createRef();
  const { websiteName } = useGlobalState();

  React.useEffect(() => {
    if (window.innerWidth > 991) {
      const updateScroll = () => {
        let windowScrollTop = window.pageYOffset / 3;
        pageHeader.current.style.transform =
          "translate3d(0," + windowScrollTop + "px,0)";
      };
      window.addEventListener("scroll", updateScroll);
      return function cleanup() {
        window.removeEventListener("scroll", updateScroll);
      };
    }
  });

  return (
	(region ?
	    <>
	      <div className="page-header clear-filter" filter-color="blue">
	        <div
	          className="page-header-image"
	          style={{
	            backgroundImage: `url(data:${region.image.type};base64,${region.image.base64})`
	          }}
	          ref={pageHeader}
	        ></div>
	        <Container>
	          <div className="content-center brand">
	            <img
	              alt="..."
	              className="n-logo"
	              src={`data:${region.logo.type};base64,${region.logo.base64}`}
	            ></img>
	            <h1 className="h1-seo">{name}</h1>
	            <h3>{region.name}</h3>
	          </div>          
	        </Container>
	      </div>
	    </>
    : <div>NO CONTENT FOUND!</div>
    )
  );
}

export default IndexHeader;
