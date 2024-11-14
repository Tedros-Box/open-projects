import React from "react";

// reactstrap components
import {
  Card,
  CardHeader,
  CardBody,
  NavItem,
  NavLink,
  Nav,
  TabContent,
  TabPane,
  Container,
  Row,
  Col,
} from "reactstrap";

// core components

function Tabs({ title, items }) {
  const [pills, setPills] = React.useState("0");

  return (
    <>
      <div className="section section-tabs">
        <Container>
          <Row>
            <Col className="ml-auto mr-auto" md="10" xl="6">
              <p className="category">{title}</p>
              <Card>
                <CardHeader>
                  <Nav
                    className="nav-tabs-neutral justify-content-center"
                    data-background-color="blue"
                    role="tablist"
                    tabs
                  >
                    {items.map((item, index) => (
                      <NavItem key={index}>
                        <NavLink
                          className={pills === String(index) ? "active" : ""}
                          href="#pablo"
                          onClick={(e) => {
                            e.preventDefault();
                            setPills(String(index));
                          }}
                        >
                          {item.name}
                        </NavLink>
                      </NavItem>
                    ))}
                  </Nav>
                </CardHeader>
                <CardBody>
                  <TabContent className="text-center" activeTab={"pills" + pills}>
                    {items.map((item, index) => (
                      <TabPane tabId={"pills" + String(index)} key={index}>
                        {item.image && item.image.base64 && item.image.type ? (
                          <img
                            src={`data:${item.image.type};base64,${item.image.base64}`}
                            alt={item.name}
                            style={{ width: "auto", height: "auto", marginBottom: "10px" }}
                          />
                        ) : null}
                        <div
		                  dangerouslySetInnerHTML={{ __html: item.content }}
		                />
                      </TabPane>
                    ))}
                  </TabContent>
                </CardBody>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
}

export default Tabs;