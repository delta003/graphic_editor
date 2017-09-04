package actions;

/**
 * Created by Marko on 01-Apr-15.
 *
 * All actions from Menu and Toolbar.
 */
public class ActionManager {

    private OpenWorkspaceAction openWorkspaceAction;
    private NewProjectAction newProjectAction;
    private OpenProjectAction openProjectAction;
    private SaveProjectAction saveProjectAction;
    private SaveWorkspaceAction saveWorkspaceAction;
    private DeleteProjectAction deleteProjectAction;
    private NewDiagramAction newDiagramAction;
    private CloseDiagramAction closeDiagramAction;
    private CloseAllDiagramsAction closeAllDiagramsAction;
    private DeleteDiagramAction deleteDiagramAction;
    private CascadeAction cascadeAction;
    private TileHorizontallyAction tileHorizontallyAction;
    private TileVerticallyAction tileVerticallyAction;
    private PreviousDiagramAction previousDiagramAction;
    private NextDiagramAction nextDiagramAction;
    private OpenAllDiagramsFromProjectAction openAllDiagramsFromProjectAction;
    private CloseAllDiagramsFromProjectAction closeAllDiagramsFromProjectAction;
    private RenameAction renameAction;
    private AboutAction aboutAction;
    private PHandCursorAction pHandCursorAction;
    private PRectangleAction pRectangleAction;
    private PCircleAction pCircleAction;
    private PTriangleAction pTriangleAction;
    private PStarAction pStarAction;
    private PLinkAction pLinkAction;
    private SearchAction searchAction;
    private SelectAllElementsAction selectAllElementsAction;
    private DeleteElementsAction deleteElementsAction;
    private ElementPropertiesAction elementPropertiesAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private LassoZoomAction lassoZoomAction;
    private BestFitZoomAction bestFitZoomAction;
    private RotateLeftAction rotateLeftAction;
    private RotateRightAction rotateRightAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        openWorkspaceAction = new OpenWorkspaceAction();
        newProjectAction = new NewProjectAction();
        openProjectAction = new OpenProjectAction();
        saveProjectAction = new SaveProjectAction();
        saveWorkspaceAction = new SaveWorkspaceAction();
        deleteProjectAction = new DeleteProjectAction();
        newDiagramAction = new NewDiagramAction();
        closeDiagramAction = new CloseDiagramAction();
        closeAllDiagramsAction = new CloseAllDiagramsAction();
        deleteDiagramAction = new DeleteDiagramAction();
        cascadeAction = new CascadeAction();
        tileHorizontallyAction = new TileHorizontallyAction();
        tileVerticallyAction = new TileVerticallyAction();
        previousDiagramAction = new PreviousDiagramAction();
        nextDiagramAction = new NextDiagramAction();
        openAllDiagramsFromProjectAction = new OpenAllDiagramsFromProjectAction();
        closeAllDiagramsFromProjectAction = new CloseAllDiagramsFromProjectAction();
        renameAction = new RenameAction();
        aboutAction = new AboutAction();
        pHandCursorAction = new PHandCursorAction();
        pRectangleAction = new PRectangleAction();
        pCircleAction = new PCircleAction();
        pTriangleAction = new PTriangleAction();
        pStarAction = new PStarAction();
        pLinkAction = new PLinkAction();
        searchAction = new SearchAction();
        deleteElementsAction = new DeleteElementsAction();
        selectAllElementsAction = new SelectAllElementsAction();
        elementPropertiesAction = new ElementPropertiesAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        bestFitZoomAction = new BestFitZoomAction();
        lassoZoomAction = new LassoZoomAction();
        rotateLeftAction = new RotateLeftAction();
        rotateRightAction = new RotateRightAction();
    }

    public OpenWorkspaceAction getOpenWorkspaceAction() {
        return openWorkspaceAction;
    }

    public void setOpenWorkspaceAction(OpenWorkspaceAction openWorkspaceAction) {
        this.openWorkspaceAction = openWorkspaceAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public void setNewProjectAction(NewProjectAction newProjectAction) {
        this.newProjectAction = newProjectAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public void setOpenProjectAction(OpenProjectAction openProjectAction) {
        this.openProjectAction = openProjectAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public void setSaveProjectAction(SaveProjectAction saveProjectAction) {
        this.saveProjectAction = saveProjectAction;
    }

    public SaveWorkspaceAction getSaveWorkspaceAction() {
        return saveWorkspaceAction;
    }

    public void setSaveWorkspaceAction(SaveWorkspaceAction saveWorkspaceAction) {
        this.saveWorkspaceAction = saveWorkspaceAction;
    }

    public DeleteProjectAction getDeleteProjectAction() {
        return deleteProjectAction;
    }

    public void setDeleteProjectAction(DeleteProjectAction deleteProjectAction) {
        this.deleteProjectAction = deleteProjectAction;
    }

    public NewDiagramAction getNewDiagramAction() {
        return newDiagramAction;
    }

    public void setNewDiagramAction(NewDiagramAction newDiagramAction) {
        this.newDiagramAction = newDiagramAction;
    }

    public CloseDiagramAction getCloseDiagramAction() {
        return closeDiagramAction;
    }

    public void setCloseDiagramAction(CloseDiagramAction closeDiagramAction) {
        this.closeDiagramAction = closeDiagramAction;
    }

    public CloseAllDiagramsAction getCloseAllDiagramsAction() {
        return closeAllDiagramsAction;
    }

    public void setCloseAllDiagramsAction(CloseAllDiagramsAction closeAllDiagramsAction) {
        this.closeAllDiagramsAction = closeAllDiagramsAction;
    }

    public DeleteDiagramAction getDeleteDiagramAction() {
        return deleteDiagramAction;
    }

    public void setDeleteDiagramAction(DeleteDiagramAction deleteDiagramAction) {
        this.deleteDiagramAction = deleteDiagramAction;
    }

    public CascadeAction getCascadeAction() {
        return cascadeAction;
    }

    public void setCascadeAction(CascadeAction cascadeAction) {
        this.cascadeAction = cascadeAction;
    }

    public TileHorizontallyAction getTileHorizontallyAction() {
        return tileHorizontallyAction;
    }

    public void setTileHorizontallyAction(TileHorizontallyAction tileHorizontallyAction) {
        this.tileHorizontallyAction = tileHorizontallyAction;
    }

    public TileVerticallyAction getTileVerticallyAction() {
        return tileVerticallyAction;
    }

    public void setTileVerticallyAction(TileVerticallyAction tileVerticallyAction) {
        this.tileVerticallyAction = tileVerticallyAction;
    }

    public PreviousDiagramAction getPreviousDiagramAction() {
        return previousDiagramAction;
    }

    public void setPreviousDiagramAction(PreviousDiagramAction previousDiagramAction) {
        this.previousDiagramAction = previousDiagramAction;
    }

    public NextDiagramAction getNextDiagramAction() {
        return nextDiagramAction;
    }

    public void setNextDiagramAction(NextDiagramAction nextDiagramAction) {
        this.nextDiagramAction = nextDiagramAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }

    public void setAboutAction(AboutAction aboutAction) {
        this.aboutAction = aboutAction;
    }

    public CloseAllDiagramsFromProjectAction getCloseAllDiagramsFromProjectAction() {
        return closeAllDiagramsFromProjectAction;
    }

    public void setCloseAllDiagramsFromProjectAction(CloseAllDiagramsFromProjectAction closeAllDiagramsFromProjectAction) {
        this.closeAllDiagramsFromProjectAction = closeAllDiagramsFromProjectAction;
    }

    public OpenAllDiagramsFromProjectAction getOpenAllDiagramsFromProjectAction() {
        return openAllDiagramsFromProjectAction;
    }

    public void setOpenAllDiagramsFromProjectAction(OpenAllDiagramsFromProjectAction openAllDiagramsFromProjectAction) {
        this.openAllDiagramsFromProjectAction = openAllDiagramsFromProjectAction;
    }

    public RenameAction getRenameAction() {
        return renameAction;
    }

    public void setRenameAction(RenameAction renameAction) {
        this.renameAction = renameAction;
    }

    public PHandCursorAction getpHandCursorAction() {
        return pHandCursorAction;
    }

    public void setpHandCursorAction(PHandCursorAction pHandCursorAction) {
        this.pHandCursorAction = pHandCursorAction;
    }

    public PRectangleAction getpRectangleAction() {
        return pRectangleAction;
    }

    public void setpRectangleAction(PRectangleAction pRectangleAction) {
        this.pRectangleAction = pRectangleAction;
    }

    public PCircleAction getpCircleAction() {
        return pCircleAction;
    }

    public void setpCircleAction(PCircleAction pCircleAction) {
        this.pCircleAction = pCircleAction;
    }

    public PTriangleAction getpTriangleAction() {
        return pTriangleAction;
    }

    public void setpTriangleAction(PTriangleAction pTriangleAction) {
        this.pTriangleAction = pTriangleAction;
    }

    public PStarAction getpStarAction() {
        return pStarAction;
    }

    public void setpStarAction(PStarAction pStarAction) {
        this.pStarAction = pStarAction;
    }

    public PLinkAction getpLinkAction() {
        return pLinkAction;
    }

    public void setpLinkAction(PLinkAction pLinkAction) {
        this.pLinkAction = pLinkAction;
    }

    public SearchAction getSearchAction() {
        return searchAction;
    }

    public void setSearchAction(SearchAction searchAction) {
        this.searchAction = searchAction;
    }

    public SelectAllElementsAction getSelectAllElementsAction() {
        return selectAllElementsAction;
    }

    public void setSelectAllElementsAction(SelectAllElementsAction selectAllElementsAction) {
        this.selectAllElementsAction = selectAllElementsAction;
    }

    public DeleteElementsAction getDeleteElementsAction() {
        return deleteElementsAction;
    }

    public void setDeleteElementsAction(DeleteElementsAction deleteElementsAction) {
        this.deleteElementsAction = deleteElementsAction;
    }

    public ElementPropertiesAction getElementPropertiesAction() {
        return elementPropertiesAction;
    }

    public void setElementPropertiesAction(ElementPropertiesAction elementPropertiesAction) {
        this.elementPropertiesAction = elementPropertiesAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public ZoomInAction getZoomInAction() {
        return zoomInAction;
    }

    public void setZoomInAction(ZoomInAction zoomInAction) {
        this.zoomInAction = zoomInAction;
    }

    public ZoomOutAction getZoomOutAction() {
        return zoomOutAction;
    }

    public void setZoomOutAction(ZoomOutAction zoomOutAction) {
        this.zoomOutAction = zoomOutAction;
    }

    public LassoZoomAction getLassoZoomAction() {
        return lassoZoomAction;
    }

    public void setLassoZoomAction(LassoZoomAction lassoZoomAction) {
        this.lassoZoomAction = lassoZoomAction;
    }

    public BestFitZoomAction getBestFitZoomAction() {
        return bestFitZoomAction;
    }

    public void setBestFitZoomAction(BestFitZoomAction bestFitZoomAction) {
        this.bestFitZoomAction = bestFitZoomAction;
    }

    public RotateLeftAction getRotateLeftAction() {
        return rotateLeftAction;
    }

    public void setRotateLeftAction(RotateLeftAction rotateLeftAction) {
        this.rotateLeftAction = rotateLeftAction;
    }

    public RotateRightAction getRotateRightAction() {
        return rotateRightAction;
    }

    public void setRotateRightAction(RotateRightAction rotateRightAction) {
        this.rotateRightAction = rotateRightAction;
    }
}
