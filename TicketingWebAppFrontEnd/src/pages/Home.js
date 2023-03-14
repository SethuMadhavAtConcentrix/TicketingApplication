import Base from "../components/Base";
import Event from "../event2.jpg";

const Home = () => {
  return (
    <Base>
      <div
        className="container text-center"
        style={{
          backgroundImage: `url(${Event})`,
          height: "100%",
          width: "100%",
        }}
      >
        <h5>
          <i>Welcome to</i>
        </h5>

        <h3
          style={{
            textShadow: "2px 2px 5px green",
            fontFamily: "lucida handwriting",
          }}
        >
          <b>Event Application Website </b>
        </h3>
        <p>
          A page where you can find <i>Events</i> related to sports, music,
          cultural and many other
        </p>
        <p>
          Book your tickets right now to be a part of this wonderful events
          happening around.
        </p>
        <h6>Not only being participating in an event</h6>
        <h5> but also can create your own event too</h5>
        <h4>Sounds Interesting...</h4>
        <h1>Hi</h1>
        <h2
          style={{
            textShadow: "2px 2px 5px red",
            fontFamily: "cursive",
          }}
        >
          Looking forward to connect...
        </h2>
        <h3>Register to the application</h3>
        <h5
          style={{
            textShadow: "2px 2px 5px blue",
          }}
        >
          To own the tickets for the event
        </h5>
      </div>
    </Base>
  );
};
export default Home;
