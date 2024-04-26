import React, { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getPostsByUserId, savePost } from "../../app/actions/post.actions";
import storage from "../../util/firebaseConfig";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";

function Recipies() {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);
  const fileInputRef = useRef(null);

  const [caption, setCaption] = React.useState("");
  const [imgLink, setImgLink] = React.useState("");
  const [desc, setDesc] = React.useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const post = {
      userId: user.userId,
      caption,
      imgLink,
      desc,
    };
    await dispatch(savePost(post));
    await dispatch(getPostsByUserId(user.userId));
    setCaption("");
    setImgLink("");
    setDesc("");
    fileInputRef.current.value = "";

  };

  const uploadImage = (e) => {
    const files = e.target.files;
  
    if (files.length === 0) {
      alert("Please upload at least one image!");
      return;
    }
  
    // upload up to 4 images
    const maxImages = 4;
    const numImages = Math.min(maxImages, files.length);
  
    for (let i = 0; i < numImages; i++) {
      const file = files[i];
      const storageRef = ref(storage, `/posts/${file.name}`);
  
      const uploadTask = uploadBytesResumable(storageRef, file);
  
      uploadTask.on(
        "state_changed",
        (snapshot) => {
          Math.round((snapshot.bytesTransferred / snapshot.totalBytes) * 100);
        },
        (err) => console.log(err),
        () => {
          getDownloadURL(uploadTask.snapshot.ref).then((url) => {
            setImgLink((prevLinks) => [...prevLinks, url]);
          });
        }
      );
    }
  };
  
  
  return (
    <div className="container mb-3 card create-card">
      <div className="card-body">
        <form onSubmit={handleSubmit}>
          <center><h1 className="mt-2">Eat Healthy</h1></center>
          <h2 className="mt-2">Share Your Recipies With Us</h2>
          <div className="mt-2 mb-3">
            <label className="form-label"></label>
            <input
              type="text"
              lassName="form-control"
              placeholder="Enter the recipe name"
              value={caption}
              onChange={(e) => setCaption(e.target.value)}
            />
            <input 
              type="text"
              style={{marginTop: '10px'}}
              className="form-control"
              placeholder="Enter the recipe description"
              value={desc}
              onChange={(e) => setDesc(e.target.value)}
            />
          </div>
            <i>*maximum 4 images</i>
          <div className="mb-3">
            {imgLink && (
              <img
                src={imgLink}
                className="img-fluid me-3"
                alt="Profile"
              />
            )}

            <input
              type="file"
              className="form-control"
              onChange={(e) => uploadImage(e)}
              ref={fileInputRef}
              multiple 
            />
          </div>

          <button type="submit" className="btn btn-outline-primary">
            POST
          </button>
        </form>
      </div>
    </div>
  );
}

export default Recipies;
