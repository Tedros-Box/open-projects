/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container } from "reactstrap";

function DarkFooter() {
  return (
    <footer className="footer" data-background-color="black">
      <Container>
        <nav>
          <ul>
            <li>
              <a
                href=""
                target="_blank"
              >
                Tedros
              </a>
            </li>
            <li>
              <a
                href=""
                target="_blank"
              >
                About Us
              </a>
            </li>
            <li>
              <a
                href=""
                target="_blank"
              >
                GitHub
              </a>
            </li>
          </ul>
        </nav>
        <div className="copyright" id="copyright">
          © {new Date().getFullYear()}, Created by{" "}
          <a
            href=""
            target="_blank"
          >
            Tedros
          </a>
          . Coded by{" "}
          <a
            href=""
            target="_blank"
          >
            Davis Dun
          </a>
          .
        </div>
      </Container>
    </footer>
  );
}

export default DarkFooter;
