const Header = () => {	
	return (
    <header id="header">
		<div>
			<img alt="" style={{float:'left'}} src="images/logo-pingeon-transparent.png" />
			<span style={{verticalAlign:'middle', fontWeight: 'bold'}}><img alt="" src="images/riosdevida.png" /></span>
		</div>
		<nav>
			<ul>
				<li><a href="#intro">Intro</a></li>
				<li><a href="#one">What I Do</a></li>
				<li><a href="#two">Who I Am</a></li>
				<li><a href="#work">My Work</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
		</nav>
	</header>
  );
}

export default Header;