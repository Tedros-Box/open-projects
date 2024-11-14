import React from "react";
import { useGlobalStateUpdate } from "../../GlobalState";
// reactstrap components
import {
  Collapse,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
  NavbarBrand,
  Navbar,
  NavItem,
  NavLink,
  Nav,
  Container
} from "reactstrap";

function IndexNavbar({name, elements}) {
  const { updateLanguage, updateLocation } = useGlobalStateUpdate();
  const [navbarColor, setNavbarColor] = React.useState("navbar-transparent");
  const [collapseOpen, setCollapseOpen] = React.useState(false);

  const handleElementChange = (e, t) => {
    if (t === "LANGUAGE") updateLanguage(e);
    else updateLocation(e);
  };

  React.useEffect(() => {

    const updateNavbarColor = () => {
      if (
        document.documentElement.scrollTop > 399 ||
        document.body.scrollTop > 399
      ) {
        setNavbarColor("");
      } else {
        setNavbarColor("navbar-transparent");
      }
    };
    window.addEventListener("scroll", updateNavbarColor);
    return function cleanup() {
      window.removeEventListener("scroll", updateNavbarColor);
    };
  }, []);

  return (
    <>
      {collapseOpen ? (
        <div
          id="bodyClick"
          onClick={() => {
            document.documentElement.classList.toggle("nav-open");
            setCollapseOpen(false);
          }}
        />
      ) : null}
      <Navbar className={"fixed-top " + navbarColor} expand="lg" color="info">        
          <Container>
            <div className="navbar-translate">
              <NavbarBrand
                href="#"
                target="_blank"
                id="navbar-brand"
              >
                {name}
              </NavbarBrand>
            </div>
            <Collapse className="justify-content-end" isOpen={collapseOpen} navbar>
              {elements && (
                <Nav navbar>
                  {elements.map((element, indexElem) => (
                    element.type === "LANGUAGE" || element.type === "LOCATION" ? (
                      <UncontrolledDropdown nav key={indexElem}>
                        <DropdownToggle
                          caret
                          color="default"
                          href="#pablo"
                          nav
                        >
                          <p>{element.name}</p>
                        </DropdownToggle>
                        <DropdownMenu>
                          {element.values.map((val, index) => (
                            <DropdownItem
                              key={index}
                              onClick={() => handleElementChange(val.value, element.type)}
                            >
                              {val.name}
                            </DropdownItem>
                          ))}
                        </DropdownMenu>
                      </UncontrolledDropdown>
                    ) : String(element.name).length>0 ? (
                      <NavItem key={indexElem}>
                        <NavLink
                          href={element.name}
                          target="_blank"
                          id={`${element.type}-tooltip`}
                        >
                          <i className={element.type === "Facebook" ? "fab fa-facebook-square" : `fab fa-${element.type.toLowerCase()}`}></i>
                          <p className="d-lg-none d-xl-none">{element.type}</p>
                        </NavLink>
                      </NavItem>
                    ) : null
                  ))}
                </Nav>
              )}
            </Collapse>
          </Container> 
      </Navbar>
    </>
  );
}

export default IndexNavbar;
