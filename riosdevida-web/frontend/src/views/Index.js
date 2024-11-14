import React from "react";

// reactstrap components
// import {
// } from "reactstrap";

// core components
import IndexNavbar from "components/Navbars/IndexNavbar.js";
import IndexHeader from "components/Headers/IndexHeader.js";
import DarkFooter from "components/Footers/DarkFooter.js";


import { useGlobalState } from "../GlobalState";

// sections for this page
import TextContent from "./index-sections/TextContent.js";
//import BasicElements from "./index-sections/BasicElements.js";
//import Navbars from "./index-sections/Navbars.js";
import Tabs from "./index-sections/Tabs.js";
//import Pagination from "./index-sections/Pagination.js";
//import Notifications from "./index-sections/Notifications.js";
//import Typography from "./index-sections/Typography.js";
//import Javascript from "./index-sections/Javascript.js";
import Carousel from "./index-sections/Carousel.js";
//import NucleoIcons from "./index-sections/NucleoIcons.js";
//import CompleteExamples from "./index-sections/CompleteExamples.js";
//import SignUp from "./index-sections/SignUp.js";
//import Examples from "./index-sections/Examples.js";
//import Download from "./index-sections/Download.js";

function Index() {
	
  const { fetchedData } = useGlobalState();
	
  React.useEffect(() => {
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;
    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  });
  return (
	fetchedData && (
    <>
      <IndexNavbar {...fetchedData.info} />       
      <div className="wrapper">
        <IndexHeader {...fetchedData} />
        	{fetchedData.region 
        	  ?	<div className="main">
		        	{fetchedData.region.contents.map((content, index) => (
						content.type === 'TEXT' 
		    				? <TextContent key={index} {...content} />
		    				: content.type === 'TABS'
		      					? <Tabs key={index} {...content} />
		      					: <Carousel key={index} {...content} />
		          	))}
        		</div>
        	  : <div>NO CONTENT FOUND!</div>}        
        <DarkFooter />
      </div>
      
    </>
    )
  );
}
/* <BasicElements />
          <Navbars />
          <Tabs />
          <Pagination />
          <Notifications />
          <Typography />
          <Javascript />
          <Carousel />
          <NucleoIcons />
          <CompleteExamples />
          <SignUp />
          <Examples />
          <Download />
          */
export default Index;
