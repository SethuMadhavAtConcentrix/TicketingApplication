import Base from "../components/Base";
import Event from "../event2.jpg";

const Home = () => {
  return (
    <Base>
      <div className="container text-center">
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
      </div>
      <div>
        <img
          src={Event}
          alt="Event Celebration"
          style={{ height: "300px", width: "100%" }}
        />
      </div>
    </Base>
  );
};
export default Home;
