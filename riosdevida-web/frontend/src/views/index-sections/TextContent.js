import React from "react";

// reactstrap components
import { Container, Row, Col } from "reactstrap";

// core components

function TextContent({title, content}) {	
  return (
    <>
      <div className="section">
        <Container className="text-center">
          <Row className="justify-content-md-center">
            <Col lg="8" md="12">
              <h2 className="title">{title}</h2>
              <div
                  dangerouslySetInnerHTML={{ __html: content }}
                />
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
}

export default TextContent;
