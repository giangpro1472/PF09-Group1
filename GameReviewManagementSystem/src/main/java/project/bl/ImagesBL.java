package project.bl;

import java.util.List;

import project.dal.ImagesDAL;
import project.persistance.Images;

public class ImagesBL {
    public static ImagesDAL dal = new ImagesDAL();

    public List<Images> getAllImage() {
        return dal.getAllImages();
    }

    public void insertImage(int id) {
        Images images = new Images();
        images.insertImages(id);
        dal.insertImage(images);
    }

    public void displayImageByID(int id) {
        if (getAllImage().isEmpty()) {
            System.out.println("There are no images in system's database :( ");
        } else {
            for (Images images : getAllImage()) {
                if (id == images.getGameID()) {
                    images.displayImageByID();
                }
            }
        }
    }
}
