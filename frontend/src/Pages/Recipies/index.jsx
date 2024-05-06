import React, { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getPostsByUserId, savePost } from "../../app/actions/post.actions";
import storage from "../../util/firebaseConfig";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import './recipies.css';

function Recipies() {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);
  const fileInputRef = useRef(null);

  const [caption, setCaption] = React.useState(""); //usestate
  const [imgLink, setImgLink] = React.useState(""); //set state2
  const [desc, setDesc] = React.useState(""); //setstate3

  const handleSubmit = async (e) => {
    e.preventDefault();
    const post = {
      userId: user.userId, //userID
      caption, //caption
      imgLink,
      desc,
    };
    await dispatch (savePost(post));
    await dispatch(getPostsByUserId(user.userId));
    setCaption(""); // set caption
    setImgLink(""); // set image
    setDesc(""); // set description
    fileInputRef.current.value = "";

  };

  const uploadImage = (e) => { // upload image
    const files = e.target.files;
  
    if (files.length === 0) {
      alert("Please upload at least one image!");
      return;
    }
  
    // upload up to 4 images
    const maxImages = 4; //max 4 files
    const numImages = Math.min(maxImages, files.length);
  
    for (let i = 0; i < numImages; i++) {
      const file = files[i];
      const storageRef = ref(storage, `/posts/${file.name}`); //storage
  
      const uploadTask = uploadBytesResumable(storageRef, file); //task uplosad
  
      uploadTask.on(
        "state_changed",
        (snapshot) => {
          Math.round((snapshot.bytesTransferred / snapshot.totalBytes) * 100); //rounding
        },
        (err) => console.log(err), // error
        () => {
          getDownloadURL(uploadTask.snapshot.ref).then((url) => {
            setImgLink((prevLinks) => [...prevLinks, url]); //setImglink
          });
        }
      );
    }
  };
  
  
  return (
    <div className="recipe" style={{ backgroundImage: 'url("https://www.shutterstock.com/image-photo/food-background-spices-herbs-utensil-260nw-2255294345.jpg")', backgroundSize: 'cover', backgroundPosition: 'center', width: '100%' , height: '100%'}}>
      <div className="card-body" >
        <form onSubmit={handleSubmit}>
          <center><h1 className="mt-2">Eat Healthy </h1> </center> 
          <div style={{height: '30px'}}></div>
          <center><h2 className="mt-2">Share Your all Recipies With Us </h2>
          <div className="mt-2 mb-3">
            <label className="form-label "></label>
            <input
              type="text"
              style={{marginTop: '50px', width: '500px'}} // styles
              className="form-control"
              placeholder="Please Enter the recipe name"
              value={caption}
              onChange={(e) =>  setCaption(e.target.value)}
            />
            <br></br>
            <input 
              type="text"
              style={{marginTop: '10px',  width: '501px'}}
              className="form-control "
              placeholder=" Please Enter the recipe description " //placeholder
              value={desc}
              onChange={(e) => setDesc(e.target.value)} //setdesc value
            />
          </div>
            <i>*maximum 4 images </i>
          <div className="mb-3">
            {imgLink && (
              <img
                src={imgLink} // imglink
                className="img-fluid me-3"
                
                alt="Profile"
              />
            )}

            <input
              type="file"
              className="form-control "
              style={{width: '500px'}} //styles
              onChange={(e) => uploadImage(e)} // uploadimg
              ref={fileInputRef} // fileref
              multiple 
            />
          </div></center>

          <br></br>

          <center><button style={{marginBottom: '200px'}} type="submit" className="btn btn-outline-primary">
            POST 
          </button></center> 
        </form>
      </div>
    </div>
  );
}

export default Recipies;
